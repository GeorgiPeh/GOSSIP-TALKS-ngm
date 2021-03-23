package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Paginated list of Gossips.
 * <p>
 * numberOfElements*	integer
 * The number of elements returned in the content.
 * <p>
 * totalElements*	integer
 * The number of elements totally available.
 * <p>
 * content*	[...]
 */
public class GossipListDTO
{

  @PositiveOrZero
  private int             numberOfElements;
  @PositiveOrZero
  private int             totalElements;
  @NotNull
  private List<GossipDTO> content = new ArrayList<>();

  public GossipListDTO()
  {
  }

  public int getNumberOfElements()
  {
    return numberOfElements;
  }

  public GossipListDTO setNumberOfElements(int numberOfElements)
  {
    this.numberOfElements = numberOfElements;
    return this;
  }

  public int getTotalElements()
  {
    return totalElements;
  }

  public GossipListDTO setTotalElements(int totalElements)
  {
    this.totalElements = totalElements;
    return this;
  }

  public List<GossipDTO> getContent()
  {
    return content;
  }

  public GossipListDTO setContent(List<GossipDTO> content)
  {
    this.content = content;
    return this;
  }
}
