package bg.codeacademy.spring.gossiptalks.dto;
/**
 * description:
 * A gossip talk.
 * <p>
 * text*	string
 * maxLength: 255
 * A common-mark formatted text. For safety reasons HTML entities must be forbidden.
 * <p>
 * id*	string
 * pattern: [A-Z0-9]+
 * A base32 representation of the gossip ID.
 * <p>
 * username*	string
 * pattern: ^[a-z0-8\\.\\-]+$
 * A short nick name of the user.
 * <p>
 * datetime*	string($date-time)
 * When this gossip was created.
 */

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class GossipDTO
{
  @Size(min = 2, max = 255, message = "Between 2 and 255 characters!")
  @NotNull
  private String        text;
  @NotNull
  @NotEmpty
  @Pattern(regexp = "[A-Z0-9]+")
  private String        id;
  @NotNull
  @Size(min = 4, max = 26)
  @Pattern(regexp = "^[a-z0-8\\\\.\\\\-]+$")
  private String        username;
  @NotNull
  @DateTimeFormat
  private LocalDateTime date;

  public GossipDTO()
  {
  }

  public String getText()
  {
    return text;
  }

  public GossipDTO
  setText(String text)
  {
    this.text = text;
    return this;
  }

  public String getId()
  {
    return id;
  }

  public GossipDTO setId(String id)
  {
    this.id = id;
    return this;
  }

  public GossipDTO setId(Integer id)
  {
    this.id = Integer.toString(id, 32).toUpperCase();
    return this;
  }

  public String getUsername()
  {
    return username;
  }

  public GossipDTO setUsername(String username)
  {
    this.username = username;
    return this;
  }

  public LocalDateTime getDate()
  {

    return LocalDateTime.now();
  }

  public GossipDTO setDate(LocalDateTime date)
  {
    this.date = date;
    return this;
  }
}