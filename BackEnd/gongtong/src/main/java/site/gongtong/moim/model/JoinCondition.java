package site.gongtong.moim.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JoinCondition {
    private int moimId;      // 모임 아이디

    private String memberId; // 유저 이메일
}
