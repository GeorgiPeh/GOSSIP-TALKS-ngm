package com.example.demo.dto;

/**
 * description:
 * Follow or unfollow user.
 * <p>
 * follow*	boolean
 * True to follow the user, and False to unfollow.
 */
public class FollowRequestDTO
{

  private boolean follow;

  public FollowRequestDTO()
  {
  }

  public boolean isFollow()
  {
    return follow;
  }

  public FollowRequestDTO setFollow(boolean follow)
  {
    this.follow = follow;
    return this;
  }
}
