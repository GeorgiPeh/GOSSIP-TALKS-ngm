package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * text*	string
 * maxLength: 255
 * A common-mark formatted text.
 * For safety reasons HTML entities
 * must be forbidden.
 */
public class CreateGossipRequestDTO
{
  @Size(min = 2, max = 255, message = "Between 2 and 255 characters!")
  @NotNull
  private String text;

  public CreateGossipRequestDTO()
  {
  }

  public String getText()
  {
    return text;
  }

  public CreateGossipRequestDTO setText(String text)
  {
    this.text = text;
    return this;
  }
}
