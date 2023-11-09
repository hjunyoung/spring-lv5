package com.example.springlv5.exception;

public class DuplicatedException extends RuntimeException{

     private ErrorCode errorCode;

     public DuplicatedException(ErrorCode errorCode) {
          super(errorCode.getMessage());
          this.errorCode = errorCode;
     }

     public DuplicatedException(String message, ErrorCode errorCode) {
          super(message);
          this.errorCode = errorCode;
     }
}
