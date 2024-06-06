package com.rwl.bit_coin.Security.payload.response;

public class MessageResponse {
  private String message;

  public String getMessage() {
    return message;
  }

  public MessageResponse(String message) {
    this.message = message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
