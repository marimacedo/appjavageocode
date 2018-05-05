/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endereco;

import java.io.Serializable;

/**
 *
 * @author maric
 */
public class AccessResponse implements Serializable
{
  private boolean access;
  private String reason;

  public boolean hasAccess()
  {
    return access;
  }

  public void setAccess(boolean access)
  {
    this.access = access;
  }

  public String getReason()
  {
    return reason;
  }

  public void setReason(String reason)
  {
    this.reason = reason;
  }

  @Override
  public String toString()
  {
    return "AccessResponse{" + access + ", " + reason + '}';
  }
}
