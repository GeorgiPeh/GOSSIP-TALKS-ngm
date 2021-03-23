package bg.codeacademy.spring.gossiptalks.model;

import bg.codeacademy.spring.gossiptalks.validation.NotHTML;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gossips")
public class Gossip
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer       id;
  @NotNull
  @Size(min = 2, max = 256, message = "Comment between 2 and 256 characters!")
  private String        text;
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  private String        username;
  @OneToOne
  private User          user;
  @NotNull
  @DateTimeFormat
  private LocalDateTime localDateTime = LocalDateTime.now();

  public Gossip()
  {
  }

  public Gossip(Integer id, @NotNull(message = "Provide content!") @Size(min = 2, max = 256, message = "Comment between 2 and 256 characters!") String text, @Valid User user, LocalDateTime localDateTime)
  {
    this.id = id;
    this.text = text;
    this.user = user;
    this.localDateTime = localDateTime;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public LocalDateTime getLocalDateTime()
  {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime)
  {
    this.localDateTime = localDateTime;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}