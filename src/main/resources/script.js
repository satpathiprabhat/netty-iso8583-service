import http from "k6/http";
import { check,sleep } from "k6";
import { Rate } from "k6/metrics";

var failRate = new Rate("Failed Request");

export let options = {
    //vus:50,
    //duration:"2m",
    stages:[
    {duration:"30s", target:50}
    ],
    thresholds: {"Faild Request":["rate<0.1"]}
};

export default function(){
	var URL = "http://localhost:9000/initiate/transation";

	var payload = JSON.stringify({
  "debitDetail": {
    "customerId": "106000000091",
    "debitAccountNumber": "50180000001114",
    "mmid": "123456",
    "name": "prabhat satpathi",
    "mobileNumber": "7987541721"
  },
  "creditDetail": {
    "customerId": "909010043780236",
    "creditAccountNumber": "123456789786",
    "mmid": "123456",
    "mobileNumber": "9987645755",
    "ifscCode": "ICIC000047",
    "name": "sapna satpathi"
  },
  "channelId": "MOB",
  "comments": "Testing transaction",
  "amount": 103.0,
  "transferType": "P2A"
});

	var param = {headers:{"Content-Type":"application/json"}}
	var res = http.post(URL,payload,param);

	check(res,{
		"Status ": (r) => r.status == 200,
		"Transaction Time OK ": (r) => r.timings.duration < 200
	});

	failRate.add(res.status != 200);
	sleep(1);
}
