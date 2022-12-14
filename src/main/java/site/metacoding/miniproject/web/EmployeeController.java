// package site.metacoding.miniproject.web;

// import java.util.List;

// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import lombok.RequiredArgsConstructor;
// import site.metacoding.miniproject.domain.check.employee.EmpCheck;
// import site.metacoding.miniproject.domain.employee.Employee;
// import site.metacoding.miniproject.domain.intro.Intro;
// import site.metacoding.miniproject.domain.job.Job;
// import site.metacoding.miniproject.domain.resume.Resume;
// import site.metacoding.miniproject.domain.subscribe.Subscribe;
// import site.metacoding.miniproject.dto.ResponseDto;
// import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;

// import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
// import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpUpdateReqDto;
// import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpUpdateRespDto;
// import site.metacoding.miniproject.service.EmployeeService;
// import site.metacoding.miniproject.service.IntroService;
// import site.metacoding.miniproject.service.JobService;
// import site.metacoding.miniproject.service.ResumeService;

// @RequiredArgsConstructor
// @Controller
// public class EmployeeController {

// private final EmployeeService employeeService;
// private final ResumeService resumeService;
// private final IntroService introService;
// private final JobService jobService;
// private final HttpSession session;

// @PostMapping("/emp/join2")
// public @ResponseBody ResponseDto<?> login(@RequestBody EmpLoginReqDto
// empLoginReqDto,
// HttpServletResponse response) {
// System.out.println("===============");
// System.out.println(empLoginReqDto.isRemember());
// System.out.println("===============");

// // if (loginDto.isRemember() == true) {
// // Cookie cookie = new Cookie("employeeUsername",
// // loginDto.getEmployeeUsername());
// // cookie.setMaxAge(60 * 60 * 24);
// // response.addCookie(cookie);

// // } else {
// // Cookie cookie = new Cookie("employeeUsername", null);
// // cookie.setMaxAge(0);
// // response.addCookie(cookie);
// // }

// Employee principal = employeeService.?????????(empLoginReqDto);
// if (principal == null) {
// return new ResponseDto<>(-1, "???????????????", null);
// }
// session.setAttribute("empprincipal", principal);
// return new ResponseDto<>(1, "???????????????", null);
// }

// @GetMapping("/es/emp/subscription")
// public String subscriptionList() {// ??????????????? ?????? ?????????????????????(???????????? ?????? ????????????)
// return "employee/subscription";
// }

// // @GetMapping("/emp/companyIntroDetail/{introId}")
// // public String introDetail(@PathVariable Integer introId, Model model) {//
// // ???????????? ?????? ???????????? ????????????
// // Employee principal = (Employee) session.getAttribute("empprincipal");
// // if (principal == null) {
// // model.addAttribute("detailDto", introService.????????????????????????(introId, 0));
// // } else {
// // model.addAttribute("detailDto", introService.????????????????????????(introId,
// // principal.getEmployeeId()));
// // }
// // return "employee/coIntroDetail";
// // }

// @PostMapping("/empapi/es/emp/companyIntroDetail/{introId}/subscribe")
// public @ResponseBody ResponseDto<?> insertSub(@PathVariable Integer introId)
// {// ????????????
// Employee principal = (Employee) session.getAttribute("empprincipal");
// Subscribe subscribe = new Subscribe(principal.getEmployeeId(), introId);
// introService.????????????(subscribe);
// return new ResponseDto<>(1, "????????????", subscribe);
// }

// @DeleteMapping("/empapi/es/emp/companyIntroDetail/{introId}/subscribe/{subscribeId}")
// public @ResponseBody ResponseDto<?> deleteSub(@PathVariable Integer introId,
// @PathVariable Integer subscribeId) {// ????????????
// introService.??????????????????(subscribeId);
// return new ResponseDto<>(1, "??????????????????", null);
// }

// @GetMapping("/emp/companyList")
// public String companylist(Model model) {// ??????????????? ?????? ???????????? ????????????
// List<Intro> introList = introService.????????????????????????();
// model.addAttribute("introList", introList);
// return "employee/companyList";
// }

// @GetMapping("emp/companyList/search")
// public String getJobNoticeList(@RequestParam("jobCode") Integer jobCode,
// Model model) {
// List<Intro> jobIntroList = introService.?????????????????????????????????(jobCode);
// model.addAttribute("jobNoticeList", jobIntroList);
// return "employee/companyJobList";
// }

// @GetMapping("/es/emp/mypageInsertForm/{employeeId}")
// public String mypageResumeInsert(@PathVariable Integer employeeId, Model
// model) {// ????????? ??????, ??????, ??????, ?????? ????????? ??????
// List<Resume> resumePS = resumeService.????????????????????????(employeeId);
// model.addAttribute("resumePS", resumePS);
// session.getAttribute("principal");
// return "employee/mypageInsertForm";
// }

// @GetMapping("/es/emp/employeeInfo/{employeeId}")
// public String ?????????????????????????????????(@PathVariable Integer employeeId, Model model) {//
// ???????????? ???????????? ????????????
// // ???????????? ????????????
// List<Job> jobPS = jobService.??????????????????();
// model.addAttribute("jobPS", jobPS);
// List<EmpCheck> checkPS = employeeService.?????????????????????(employeeId);
// model.addAttribute("checkPS", checkPS);

// // ???????????????
// Employee employeePS = (Employee) session.getAttribute("empprincipal");
// /* Employee employeePS = employeeService.employeeUpdate(employeeId); */
// model.addAttribute("employee", employeePS);
// return "employee/empInfo";
// }

// @DeleteMapping("/empapi/es/emp/employeeInfo/{employeeId}")
// public @ResponseBody ResponseDto<?> ????????????(@PathVariable Integer employeeId,
// HttpServletResponse response) {
// employeeService.employeeDelete(employeeId);
// Cookie cookie = new Cookie("employeeUsername", null);
// cookie.setMaxAge(0);
// response.addCookie(cookie);
// session.invalidate();
// return new ResponseDto<>(1, "??????????????????", null);
// }

// @PutMapping("/empapi/es/emp/employeeInfo/{employeeId}")
// public @ResponseBody ResponseDto<?> ??????????????????(@PathVariable Integer employeeId,
// @RequestBody EmpUpdateReqDto empUpdateReqDto) {
// EmpUpdateRespDto empUpdateRespDtoPS =
// employeeService.employeeUpdate(employeeId,
// empUpdateReqDto);
// session.setAttribute("empprincipal", empUpdateRespDtoPS);
// return new ResponseDto<>(1, "??????????????????", null);
// }

// @PostMapping("/emp/join")
// public @ResponseBody ResponseDto<?> ????????????(@RequestBody EmpJoinReqDto
// employeeJoinDto) {
// employeeService.employeeJoin(employeeJoinDto);
// return new ResponseDto<>(1, "??????????????????", null);
// }

// @GetMapping("/logout")
// public String logout() {
// session.invalidate();
// return "redirect:/";
// }

// // =========================== ??????????????? ======================================
// // http://localhost:8000/users/usernameSameCheck?username=ssar
// @GetMapping("/emp/usernameSameCheck")
// public @ResponseBody ResponseDto<Boolean> usernameSameCheck(String
// employeeUsername) {
// boolean isSame = employeeService.????????????????????????(employeeUsername);
// return new ResponseDto<>(1, "??????", isSame);
// }

// @GetMapping("/emp/checkPassword")
// public @ResponseBody ResponseDto<Boolean> checkPassword(String
// employeePassword) {
// boolean isSame = employeeService.????????????2?????????(employeePassword);
// return new ResponseDto<>(1, "??????", isSame);
// }

// @GetMapping("/emp/checkEmail")
// public @ResponseBody ResponseDto<Boolean> checkEmail(String employeeEmail) {
// boolean isSame = employeeService.?????????????????????(employeeEmail);
// return new ResponseDto<>(1, "??????", isSame);
// }
// }
