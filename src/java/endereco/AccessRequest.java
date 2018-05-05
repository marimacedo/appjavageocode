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
public class AccessRequest implements Serializable
{
  private Long userId;
  private Long resourceId;

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }

  public Long getUserId()
  {
    return userId;
  }

  public void setResourceId(Long resourceId)
  {
    this.resourceId = resourceId;
  }

  public Long getResourceId()
  {
    return resourceId;
  }

  @Override
  public String toString()
  {
    return "AccessRequest{" + userId + ", " + resourceId + '}';
  }
}
