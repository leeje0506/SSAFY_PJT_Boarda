package site.gongtong.moim.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.JoinCondition;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimCondition;
import site.gongtong.moim.repository.MoimCustomRepository;
import site.gongtong.moim.repository.MoimMemberCustomRepository;
import site.gongtong.moim.service.MoimService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/moim")
@Slf4j
public class MoimController {
    private final MoimService moimService;
    //private final MoimCustomRepository moimCustomRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Moim>> getSortedList(@RequestParam(name="location") String location, @RequestParam(name="sort") int sorting){

        log.info("리스트 정렬 들어옴!!!");

        List<Moim> sortedMoimList = moimService.getSortedMoimList(location, sorting);

        return new ResponseEntity<>(sortedMoimList, HttpStatus.OK);
    }

    @GetMapping("/deadline")
    public ResponseEntity<List<Moim>> getDeadlineList(){
        log.info("마감임박 들어옴!!");
        return new ResponseEntity<>(moimService.getDeadlineList(), HttpStatus.OK);
    }

    // 조건
    // 1. 참여한 진행 중인 모임이 있으면 못 만들게 한다.
    @GetMapping("/checkroom")
    public ResponseEntity<Integer> checkRoom(@RequestParam(name="num") int userNum){
        log.info("진행중인 방 체크");

        return new ResponseEntity<>(moimService.checkRoom(userNum), HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<Integer> createRoom(@RequestBody MoimCondition moimCondition){
        log.info("방 만들기");
        log.info(moimCondition.toString());
        int userNum = moimCondition.getUserId();

        Moim moim = Moim.builder()
                .title(moimCondition.getTitle())
                .content(moimCondition.getContent())
                .number(moimCondition.getNumber())
                .location(moimCondition.getLocation())
                .datetime(moimCondition.getDatetime())
                .status('P')
                .build();

        int result = moimService.createRoom(moim, userNum);
        // 모임 컨디션 객체를 열어서 user_num 이랑 모임 정보를 가지고 모임방 만들고, 모임멤버 만들어서 연결
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Integer> joinMoim(@RequestBody JoinCondition joinCondition){
        log.info("방에 참여하기");

        Integer result = moimService.joinRoom(joinCondition.getMoimId(), joinCondition.getMemberId());

        // 이미 꽉 찬 방이면 result가 false, 아니면 result true
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // TODO 팔로우 기능 생기면 친구 초대 기능(알림만 보내줌) 추가해야 됨.
    @GetMapping("/friend")
    public ResponseEntity<Void> inviteFriend(@RequestParam(name="my_id") int myId, @RequestParam(name="f_id") int friendId, @RequestParam(name="moim_id") int moimId){
        log.info("친구 초대하기");
        // 내 아이디랑 친구 아이디 받아서 내 모임에 친구 추가하기
        return ResponseEntity.ok().build();
    }

//    //API 테스트용
//    @GetMapping("/test")
//    public ResponseEntity<List<Moim>> test(){
//        return new ResponseEntity<>(moimCustomRepository.getMoimWithMemberCountOrder(), HttpStatus.OK);
//    }
}
