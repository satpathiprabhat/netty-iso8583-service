package com.ps.netty.iso8583.services.utilities;

import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.ps.netty.iso8583.services.config.PropertyResolver;
import com.ps.netty.iso8583.services.models.ImpsTransactionReq;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ps.netty.iso8583.services.constants.IsoMsgConstants.*;

@Component
public class IsoMessageBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsoMessageBuilder.class);

    Iso8583Client<IsoMessage> client;
    @Autowired
    PropertyResolver propertyResolver;

    private static int PROCESSING_CODE = 900000;
    private static String DE7_PATTERN = "MMddhhmmss";
    private static String DE12_PATTERN = "HHmmss";
    private static String DE13_15_PATTERN = "MMdd";
    private static String ACCEPTOR_NAME_COUNTRY = "IN";

    @Autowired
    public IsoMessageBuilder(Iso8583Client<IsoMessage> client) {
        this.client = client;
    }

    public IsoMessage buildORMsg (ImpsTransactionReq request){

        IsoMessage msg = client.getIsoMessageFactory().newMessage(0x200);
        String beneficiaryNbin = "9229";
        StringBuilder sb = new StringBuilder();
        if (request.getTranferType().equals("P2A")){
            sb.append(beneficiaryNbin).append("00100")
              .append(StringUtils.right(request.getCreditDetails().getCreditAccountNumber(),10));
            String P2AprimaryAcctNum = sb.toString();
            msg.setField(DE2, IsoType.LLVAR.value(P2AprimaryAcctNum,19));
            msg.setField(DE3,IsoType.NUMERIC.value(PROCESSING_CODE,6));
        }else {
            sb.append(beneficiaryNbin)
              .append("00100")
              .append(request.getCreditDetails().getMobileNumber());
            String P2PprimaryAcctNum = sb.toString();
            msg.setField(DE2, IsoType.LLVAR.value(P2PprimaryAcctNum,19));
            msg.setField(DE3,IsoType.NUMERIC.value(PROCESSING_CODE,6));
        }
        msg.setField(DE4,IsoType.AMOUNT.value(request.getAmount(),12));
        msg.setField(DE7,IsoType.DATE10.value(Utils.getDateTime(DE7_PATTERN,TimeZone.GMT),10));
        Utils utils = new Utils();
        String stan =StringUtils.right(utils.generateRRN(),6);

        msg.setField(DE11,IsoType.NUMERIC.value(stan,6));
        msg.setField(DE12,IsoType.TIME.value(Utils.getDateTime(DE12_PATTERN,TimeZone.IST),6));
        msg.setField(DE13,IsoType.DATE4.value(Utils.getDateTime(DE13_15_PATTERN,TimeZone.IST),4));

        if (StringUtils.equals(request.getChannelId(),"MOB")){
            msg.setField(DE18,IsoType.NUMERIC.value(MB_MERCHANT_CATAGORY_CODE,4));
            msg.setField(DE22,IsoType.NUMERIC.value(MB_SERVICE_ENTRY_MODE,3));
        }else {
            msg.setField(DE18,IsoType.NUMERIC.value(IB_MERCHANT_CATAGORY_CODE,4));
            msg.setField(DE22,IsoType.NUMERIC.value(IB_SERVICE_ENTRY_MODE,3));
        }
        msg.setField(DE25,IsoType.NUMERIC.value(POS_CONDITION_CODE,2));
        msg.setField(DE37,IsoType.NUMERIC.value(utils.generateRRN(),12));

        /**card acceptor DE41 **/
        StringBuilder accptIdMsgBuilder = new StringBuilder();
        String termilnalId = accptIdMsgBuilder.append(propertyResolver.getCardAcceptorTerminalId())
                .append(StringUtils.right(request.getCreditDetails().getMobileNumber(),5))
                .toString();
        msg.setField(DE41,IsoType.ALPHA.value(termilnalId, termilnalId.length()));

        /**card acceptor id DE42**/
        StringBuilder acceptorIdBuilder = new StringBuilder();
        String acceptorId = acceptorIdBuilder.append("NES91")
                .append(request.getCreditDetails().getMobileNumber())
                .toString();
        msg.setField(DE42,IsoType.ALPHA.value(acceptorId,acceptorId.length()));
        /**acceptor name and location DE43 **/
        StringBuilder acceptorNameBuilder = new StringBuilder();
        String acceptorName = acceptorNameBuilder.append(propertyResolver.getCardAcceptorName())
                .append(request.getCreditDetails().getMobileNumber())
                .append(ACCEPTOR_NAME_COUNTRY).toString();
        msg.setField(DE43,IsoType.ALPHA.value(acceptorName,40));

        String debitAccountNumber = StringUtils.leftPad(request.getDebitDetail().getDebitAccountNumber(),19,"0");
        msg.setField(DE102,IsoType.LLVAR.value(debitAccountNumber,19));
        String privateField = buildPrivateFields(request,request.getChannelId());
        msg.setField(DE120,IsoType.LLLVAR.value(privateField,privateField.length()));
        return msg;
    }



    private String buildPrivateFields(ImpsTransactionReq request,String channelId){
        String transactionType = "";
        String mmidMob = "";
        String benIfsc ="";
        String isoBenAcctNumber = "";
        StringBuilder mmidMobSb = new StringBuilder();
        StringBuilder transTypSb = new StringBuilder();

        if (request.getTranferType().equals("P2A")){
            transactionType = transTypSb.append(DE_120_001_VAL).append(P2A_ORIGINAL_VALUE).toString();
            String remmiterNbin = propertyResolver.getRemmiterNbin();
            mmidMob = mmidMobSb.append(DE_120_050_VAL)
                    .append(remmiterNbin + "000" + request.getDebitDetail().getMobileNumber().length())
                    .append(remmiterNbin + "000" + request.getDebitDetail().getMobileNumber())
                    .toString();
            StringBuilder benIfscSb = new StringBuilder();
            benIfsc = benIfscSb.append(DE_120_059_VAL)
                    .append(request.getCreditDetails().getIfscCode().length())
                    .append(request.getCreditDetails().getIfscCode())
                    .toString();
            StringBuilder benAcctNumSb = new StringBuilder();
            benAcctNumSb.append(DE_120_062_VAL)
                    .append(StringUtils.leftPad(String.valueOf(request.getCreditDetails().getCreditAccountNumber().length()),3,"0"))
                    .append(request.getCreditDetails().getCreditAccountNumber()).toString();
        }else {
            transactionType = transTypSb.append(DE_120_001_VAL).append(P2P_ORIGINAL_VALUE).toString();
            mmidMob = mmidMobSb.append(DE_120_050_VAL)
                    .append((request.getDebitDetail().getMmid()+request.getDebitDetail().getMobileNumber()).length())
                    .append(request.getDebitDetail().getMmid()+request.getDebitDetail().getMobileNumber())
                    .toString();
        }

        String productIndicator = new StringBuilder().append(DE_120_002_VAL).append(channelId).toString();
        int len = request.getDebitDetail().getName().length();
        String remitterName = new StringBuilder().append(DE_120_045_VAL)
                .append(len<10?"0"+String.valueOf(len):String.valueOf(len)).toString();

        String benMAS = new StringBuilder().append(DE_120_049_VAL)
                .append("000").toString();

        StringBuilder remarkSb = new StringBuilder();
        String remarkLen = StringUtils.leftPad(String.valueOf(request.getComments().length()),3,"0");
        String remark = remarkSb.append(DE_120_051_VAL).append(remarkLen).append(request.getComments()).toString();

        String originatingChannel = new StringBuilder().append(DE_120_056_VAL).append(channelId).toString();

        /** final value with all sub elements of private field**/
        StringBuilder privateFieldSb = new StringBuilder();
        String privateField = "";
        if (request.getTranferType().equals("P2A")){
            privateField = privateFieldSb.append(transactionType)
                    .append(productIndicator)
                    .append(remitterName)
                    .append(benMAS)
                    .append(mmidMob)
                    .append(remark)
                    .append(originatingChannel)
                    .append(benIfsc)
                    .append(isoBenAcctNumber).toString();
        }else {
            privateField = privateFieldSb.append(transactionType)
                    .append(productIndicator)
                    .append(remitterName)
                    .append(benMAS)
                    .append(mmidMob)
                    .append(remark)
                    .append(originatingChannel)
                    .toString();
        }

        return privateField;
    }

    public static IsoMessage buildRetryMessage(IsoMessage msg){
        String OR_MSG_PRIVATE_FIELD = msg.getObjectValue(DE120);
        String VR_MSG_PRIVATE_FIELD ="";
        String orTranTyp = "";
        String vrTranTyp = "";
        String tranferType = DataElementParser.getDataElements(OR_MSG_PRIVATE_FIELD).get(TAG_TRAN_TYPE_ID);

        if (tranferType.equals(P2A_ORIGINAL_VALUE)){
            orTranTyp = DE_120_001_VAL.concat(P2A_ORIGINAL_VALUE);
            vrTranTyp = DE_120_001_VAL.concat(P2A_VARIFICATION_VALUE);
            VR_MSG_PRIVATE_FIELD = OR_MSG_PRIVATE_FIELD.replaceFirst(orTranTyp,vrTranTyp);
        } else if (tranferType.equals(P2P_ORIGINAL_VALUE)) {
            orTranTyp = DE_120_001_VAL.concat(P2P_ORIGINAL_VALUE);
            vrTranTyp = DE_120_001_VAL.concat(P2P_VARIFICATION_VALUE);
            VR_MSG_PRIVATE_FIELD = OR_MSG_PRIVATE_FIELD.replaceFirst(orTranTyp,vrTranTyp);
        }
        msg.setField(DE7,IsoType.DATE10.value(Utils.getDateTime(DE7_PATTERN,TimeZone.GMT),10));
        StringBuilder originalTransDataSb = new StringBuilder();
        originalTransDataSb
                .append(msg.getObjectValue(DE12).toString())
                .append(msg.getObjectValue(DE13).toString());
        DataElement originalTransData = new DataElement(TAG_ORIGINAL_TRANSACTION_DATA_ID,originalTransDataSb.toString());
        String updatedPrivateField = DataElementParser.addElementAfter(VR_MSG_PRIVATE_FIELD,TAG_BENEFICIARY_NAME_ID,originalTransData);

        msg.setField(DE120,IsoType.LLLVAR.value(updatedPrivateField,updatedPrivateField.length()));
        return msg;
    }
}
