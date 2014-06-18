package org.whdl.intermediate;

public class UndeclaredAttributeException extends Exception {
  private static final long serialVersionUID = 3901819872140384623L;

  public String name;

  public UndeclaredAttributeException(String name) {
    this.name = name;
  }

  @Override
  public String getMessage() {
    return "undeclared attribute '" + this.name + "'";
  }
}