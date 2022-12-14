package site.metacoding.miniproject.domain.intro;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroUpdateReqDto;

@NoArgsConstructor
@Getter
@Setter
public class Intro {
	private Integer introId;
	private Integer companyId;
	private String introConame;
	private String introBirth;
	private String introTask;
	private String introSal;
	private String introWellfare;
	private String introContent;
	private String introLocation;
	private Integer jobId;

	// 엔티티가 아닌 필드
	private String companyName;
	private String jobName;
	private String jobCode;
	private boolean isSubed;
	private Integer subscribeId;
	private Integer principalId;

	@Builder
	public Intro(Integer introId, Integer companyId, String introConame, String introBirth, String introTask,
			String introSal, String introWellfare, String introContent, String introLocation, Integer jobId,
			String companyName, String jobName, String jobCode) {
		this.introId = introId;
		this.companyId = companyId;
		this.introConame = introConame;
		this.introBirth = introBirth;
		this.introTask = introTask;
		this.introSal = introSal;
		this.introWellfare = introWellfare;
		this.introContent = introContent;
		this.introLocation = introLocation;
		this.jobId = jobId;
		this.companyName = companyName;
		this.jobName = jobName;
		this.jobCode = jobCode;
	}

	public void update(IntroUpdateReqDto introUpdateReqDto) {
		this.introId = introUpdateReqDto.getIntroId();
		this.companyName = introUpdateReqDto.getCompanyName();
		this.introBirth = introUpdateReqDto.getIntroBirth();
		this.introTask = introUpdateReqDto.getIntroTask();
		this.introSal = introUpdateReqDto.getIntroSal();
		this.introWellfare = introUpdateReqDto.getIntroWellfare();
		this.introContent = introUpdateReqDto.getIntroContent();
		this.introLocation = introUpdateReqDto.getIntroLocation();
		this.jobId = introUpdateReqDto.getJobId();

	}

}
