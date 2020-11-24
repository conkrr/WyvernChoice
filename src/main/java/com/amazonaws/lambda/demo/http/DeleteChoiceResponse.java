package com.amazonaws.lambda.demo.http;

public class DeleteChoiceResponse {
		public String id;
		public int statusCode;
		public String error;
		
		public DeleteChoiceResponse (String name, int statusCode) {
			this.id = name;
			this.statusCode = statusCode;
			this.error = "";
		}
		
		// 200 means success
		public DeleteChoiceResponse (String name, int statusCode, String errorMessage) {
			this.statusCode = statusCode;
			this.error = errorMessage;
			this.id = name;
		}
		
		public String toString() {
			if (statusCode / 100 == 2) {  // too cute?
				return "DeleteResponse(" + id + ")";
			} else {
				return "ErrorResult(" + id + ", statusCode=" + statusCode + ", err=" + error + ")";
			}
		
		}
}
