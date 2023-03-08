package org.example.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Board {

  private UUID id;
  private String title;
  private String content;
  private Member member;
  private LocalDateTime createdAt;

  public UUID getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Board{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", member=" + member +
        ", createdAt=" + createdAt +
        '}';
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
