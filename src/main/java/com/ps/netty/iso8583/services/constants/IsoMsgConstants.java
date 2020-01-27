package com.ps.netty.iso8583.services.constants;

import org.apache.commons.lang3.StringUtils;

public interface IsoMsgConstants {

    public final int DE2 = 2;
    public final int DE3 = 3;
    public final int DE4 = 4;
    public final int DE7 = 7;
    public final int DE11 = 11;
    public final int DE12 = 12;
    public final int DE13 = 13;
    public final int DE15 = 15;
    public final int DE18 = 18;
    public final int DE22 = 22;
    public final int DE25 = 25;
    public final int DE32 = 32;
    public final int DE37 = 37;
    public final int DE38 = 38;
    public final int DE39 = 39;
    public final int DE40 = 40;
    public final int DE41 = 41;
    public final int DE42 = 42;
    public final int DE43 = 43;
    public final int DE46 = 46;
    public final int DE70 = 70;
    public final int DE102 = 102;
    public final int DE103 = 103;
    public final int DE120 = 120;

    public static final String SUCCESS = "00";
    public static final String TIMEOUT = "91";
    public static final String NETWORK_FAILURE = "08";


    /** private field tags VALUES **/
    public static final String DE_120_001_VAL = "001002";
    public static final String DE_120_002_VAL = "002003";
    public static final String DE_120_045_VAL = "0450";
    public static final String DE_120_049_VAL = "049003";
    public static final String DE_120_050_VAL = "0500";
    public static final String DE_120_051_VAL = "051";
    public static final String DE_120_056_VAL = "056003";
    public static final String DE_120_059_VAL = "0590";
    public static final String DE_120_062_VAL = "062";

    /**private fields tags id**/
    public static final String TAG_TRAN_TYPE_ID = "001";
    public static final String TAG_PRODUCT_INDICATOR_ID = "002";
    public static final String TAG_REMITTER_NAME_ID = "045";
    public static final String TAG_BENEFICIARY_NAME_ID = "046";
    public static final String TAG_ORIGINAL_TRANSACTION_DATA_ID = "047";
    public static final String TAG_BENEFICIARY_MAS_ID = "049";
    public static final String TAG_REMITTER_MMID_AND_MOB_ID = "050";
    public static final String TAG_COMMENT_ID = "051";
    public static final String TAG_ORIGINATING_CHANNEL_ID = "056";
    public static final String TAG_BENEFICIARY_IFSC_ID = "045";
    public static final String TAG_BENEFICIARY_ACCT_ID = "045";

    /** P2A and P2P Original and verification values **/
    public static final String P2P_ORIGINAL_VALUE = "45";
    public static final String P2P_VARIFICATION_VALUE = "32";
    public static final String P2A_ORIGINAL_VALUE = "48";
    public static final String P2A_VARIFICATION_VALUE = "34";

    public static final int MB_MERCHANT_CATAGORY_CODE = 4814;
    public static final int IB_MERCHANT_CATAGORY_CODE = 4829;

    public static final String MB_SERVICE_ENTRY_MODE = "019";
    public static final String IB_SERVICE_ENTRY_MODE = "012";
    public static final int POS_CONDITION_CODE = 05;

}
