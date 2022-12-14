
package site.metacoding.miniproject.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpJoinRespDto;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.dto.subscribe.SubscribeReqDto.SubscribeSaveReqDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeRespDto.SubscribeSaveRespDto;
import site.metacoding.miniproject.service.EmployeeService;
import site.metacoding.miniproject.service.IntroService;

@RequiredArgsConstructor
@RestController
public class EmployeeApiController {

    private final IntroService introService;
    private final EmployeeService employeeService;
    private final HttpSession session;

    // 로그인
    @PostMapping("/emp/login")
    public ResponseDto<?> login(@RequestBody EmpLoginReqDto empLoginReqDto,
            HttpServletResponse response) {
        EmpSessionUser empPrincipal = employeeService.로그인(empLoginReqDto);
        if (empPrincipal == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }
        session.setAttribute("empprincipal", empPrincipal);
        return new ResponseDto<>(1, "로그인성공", null);
    }

    @PostMapping("/emp/join")
    public ResponseDto<?> employeeJoin(@RequestBody EmpJoinReqDto empJoinReqDto) {
        EmpJoinRespDto empJoinRespDto = employeeService.employeeJoin(empJoinReqDto);
        // employeeService.employeeJoin(empJoinReqDto);
        return new ResponseDto<>(1, "회원가입 성공", empJoinRespDto);
    }

    @GetMapping("/emp/companyList")
    public ResponseDto<?> findAll() {
        return new ResponseDto<>(1, "성공", introService.findAll());
    }

    // 개인이 보는기업소개 상세보기
    @GetMapping("/emp/companyIntroDetail/{introId}")
    public ResponseDto<?> findByDetail(@PathVariable Integer introId, Integer principalId) {
        return new ResponseDto<>(1, "성공", introService.findByDetail(introId, principalId));
    }

    // 구독하기
    @PostMapping("/emp/subscribe")
    public ResponseDto<?> insertSub(@RequestBody SubscribeSaveReqDto subscribeSaveReqDto) {
        // Employee principal = (Employee) session.getAttribute("empprincipal");
        SubscribeSaveRespDto subscribeSaveRespDto = introService.구독하기(subscribeSaveReqDto);
        return new ResponseDto<>(1, "구독성공", subscribeSaveRespDto);
    }

    // 구독취소
    @DeleteMapping("/emp/subscribe/{subscribeId}")
    public ResponseDto<?> deleteSub(
            @PathVariable Integer subscribeId) {
        introService.구독취소하기(subscribeId);
        return new ResponseDto<>(1, "구독취소성공", null);
    }
}
