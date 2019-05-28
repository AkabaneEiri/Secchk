package egovframework.main;

import java.awt.Frame;
import java.awt.Window;
import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.main.service.ActivityService;
import egovframework.main.service.ActivityTypeService;
import egovframework.main.service.CheckApprovalService;
import egovframework.main.service.CheckCodeService;
import egovframework.main.service.ChecklistItemService;
import egovframework.main.service.ChecklistService;
import egovframework.main.service.CodeService;
import egovframework.main.service.CtlgCodeService;
import egovframework.main.service.GroupCodeService;
import egovframework.main.service.LoginService;
import egovframework.main.service.MemberService;
import egovframework.main.service.RequestActivityService;
import egovframework.main.service.RequestChecklistService;
import egovframework.main.service.SptlyCodeService;
import egovframework.main.service.TaskDataService;
import egovframework.main.service.TaskService;
import egovframework.main.service.TroopsService;
import egovframework.main.service.VO.ActivityTypeVO;
import egovframework.main.service.VO.ActivityVO;
import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.CheckCodeVO;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ChecklistVO;
import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.CodeVO;
import egovframework.main.service.VO.CtlgCodeVO;
import egovframework.main.service.VO.DateVO;
import egovframework.main.service.VO.GroupCodeVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.MemberVO;
import egovframework.main.service.VO.RequestActivityVO;
import egovframework.main.service.VO.RequestChecklistVO;
import egovframework.main.service.VO.SptlyCodeVO;
import egovframework.main.service.VO.TaskDataVO;
import egovframework.main.service.VO.TaskVO;
import egovframework.main.service.VO.TroopsVO;
import egovframework.main.service.VO.UserVO;
import egovframework.main.util.CustomTimerTask;
import egovframework.main.util.DateUtil;
import egovframework.main.util.Pagination;
import egovframework.main.util.ScriptWriter;
import egovframework.main.util.excel.ExcelRead;
import egovframework.main.util.excel.ExcelReadOption;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;








@SuppressWarnings("unused")

@Controller
public class MainController {
	// 사용할 리소스 지정

	// logger
	public static Logger logger = Logger.getLogger(PatternLayout.class);
	// script writer
	public ScriptWriter scriptWriter;

	// seungwon 19.02.12 //
	private static Boolean isLogin = false;

	private static UserVO loginedUser = null;
	@Resource(name = "memberService")
	public MemberService memberService;

	@Resource(name = "taskService")
	public TaskService taskService;
	
	@Resource(name="troopsService")
	public TroopsService troopsService;

	@Resource(name = "CheckCodeService")
	public CheckCodeService checkCodeService;

   @Resource(name="CtlgCodeService")
   public CtlgCodeService ctlgCodeService; 
   
   
   @Resource(name="sptlyCodeService")
   public SptlyCodeService sptlyCodeService;
//   @Resource(name="TroopsService")
//   public TroopsService troopsService;


	// seungwon
	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "activityService")
	private ActivityService activityService;

	@Resource(name = "checklistService")
	private ChecklistService checklistService;

	@Resource(name = "checklistItemService")
	private ChecklistItemService checklistItemService;

	@Resource(name = "requestActivityService")
	private RequestActivityService requestActivityService;

	@Resource(name = "checkApprovalService")
	public CheckApprovalService checkApprovalService;
	
	@Resource(name = "requestChecklistService")
	public RequestChecklistService requestChecklistService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name = "groupCodeService")
	private GroupCodeService groupCodeService;
	
	@Resource(name = "taskDataService")
	private TaskDataService taskDataService;
	
	@Resource(name = "activityTypeService")
	private ActivityTypeService activityTypeService;

	// 메인
	@RequestMapping(value = "index.do")
	public String index(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpServletRequest request, HttpSession httpSession, ModelMap model,
			HttpServletResponse response) throws Exception {		
		if(httpSession.getAttribute("loginedUser") != null) {
			String athrt = (String) httpSession.getAttribute("SS_ATHRT");
			
			if(athrt.trim().equals("B1")) {
				// for B1
				// get activity list, request activity list, request fix checklist
				//List<ActivityVO> actList = activityService.getActivityListByLogined(loginedUser);
				List<TaskVO> taskList = taskService.getTaskListForMain(loginedUser.getSrvno());
				System.out.println("taskList : " + taskList);
				List<RequestActivityVO> reqActList = requestActivityService.getRequestActivityListVO(loginedUser.getSrvno());
				List<RequestChecklistVO> reqCheckList = requestChecklistService.getRequestedList(loginedUser);
				
				model.addAttribute("taskList", taskList);
				model.addAttribute("reqActList", reqActList);
				model.addAttribute("reqCheckList", reqCheckList);
			}
			else if(athrt.trim().equals("B2")) {
				// for B2
				// get rqst ugcylist, rqst occlist, rqst checklist
				List<CheckApprovalVO> approList = checkApprovalService.getLimitCheckApprovalList(loginedUser.getIncdt_idtf_cd().trim());
				List<RequestActivityVO> chkActList = requestActivityService.getLimitReqActList(loginedUser.getIncdt_idtf_cd().trim());
				List<RequestChecklistVO> chkCheckList = requestChecklistService.getLimitReqCheckist(loginedUser.getIncdt_idtf_cd().trim());
				
				// cut str for mainboard
				for(int i = 0; i < approList.size(); ++i) {
					if(approList.get(i).getCtlg_itm_nm() == null) {
						approList.get(i).setCtlg_itm_nm("");
					}
					
					if(approList.get(i).getCtlg_itm_nm().length() > 14)
						approList.get(i).setCtlg_itm_nm(approList.get(i).getCtlg_itm_nm().substring(0, 14) + "..");
				}
				
				for(int i = 0; i < chkActList.size(); ++i) {
					
					if(chkActList.get(i).getRsn() == null) {
						chkActList.get(i).setRsn("");
					}
					
					if(chkActList.get(i).getRsn().length() > 14)
						chkActList.get(i).setRsn(chkActList.get(i).getRsn().substring(0, 14) + "..");
				}				
				
				for(int i = 0; i < chkCheckList.size(); ++i) {
					
					if(chkCheckList.get(i).getCtlg_itm_nm() == null) {
						chkCheckList.get(i).setCtlg_itm_nm("신규 요청");
					}
					
					if(chkCheckList.get(i).getCtlg_itm_nm().length() > 14) {
						chkCheckList.get(i).setCtlg_itm_nm(chkCheckList.get(i).getCtlg_itm_nm().substring(0, 13) + "..");
					}						
				}
				
				// view				
				model.addAttribute("approList", approList);
				model.addAttribute("chkActList", chkActList);
				model.addAttribute("chkCheckList", chkCheckList);
			}			
		}
		return "./main/index";
	}

	// 로그인
	@RequestMapping(value = "login.do")
	public String login() throws Exception {
		return "./main/Login";
	}

	// 예정된 안전관리활동
	@RequestMapping(value = "ReservedSafeManagement.do")
	public String ReservedSafeManagement(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
			@ModelAttribute("timeVO") DateVO timeVO, ModelMap mod,
			HttpSession httpSession)
			throws Exception {

		if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
		}

		List<TaskVO> tempTaskList = taskService.getTaskListVO(loginedUser
				.getSrvno());

		mod.addAttribute("taskList", tempTaskList);

		return "./main/ReservedSafeManagement";
	}

	// 수시 안전관리활동
	@RequestMapping(value = "OccasionalSafeManagement.do")
	public String OccasionalSafeManagement(HttpServletRequest request, ModelMap mod) throws Exception {
		List<RequestActivityVO> requestList = requestActivityService.getRequestActivityListVO(loginedUser.getSrvno());
		
		// do trim()
		for(int i = 0; i < requestList.size(); ++i) {
			requestList.get(i).setState_cd(requestList.get(i).getState_cd().trim());
		}
		
		// paging
		int pageNo = 1; // default
		
		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);
		
		if(pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);

		Pagination pagination = new Pagination();
		pagination.setPaginationInfo(requestList, pageNo);
		
		mod.addAttribute("requestList", pagination.getListPerPage(requestList));
		mod.addAttribute("paginationInfo", pagination.getPaginationInfo());

		return "./main/OccasionalSafeManagement";
	}

	// 체크리스트
	@RequestMapping(value = "CheckListManagement.do")
	public String CheckListManagement(HttpServletRequest request, ModelMap model) throws Exception {
		// 요청한 리스트 목록
		List<RequestChecklistVO> tempRequestedList = requestChecklistService.getRequestedList(loginedUser);
		
		// paging
		int pageNo = 1; // default

		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);

		if (pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);

		Pagination pagination = new Pagination();
		pagination.setPaginationInfo(tempRequestedList, pageNo);	

		// view
		model.addAttribute("requestedList", pagination.getListPerPage(tempRequestedList));
		model.addAttribute("paginationInfo", pagination.getPaginationInfo());
		
		return "./main/CheckListManagement";
	}

	// 부대활동 모니터링
	@RequestMapping(value = "TroopsMonitoring.do")
	public String TroopsMonitoring(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, ModelMap model, HttpSession httpSession)
			throws Exception {
		Object obj = httpSession.getAttribute("loginedUser");
		String athrt = (String) httpSession.getAttribute("SS_ATHRT");
		
		if(obj != null) {
			// 최고지휘관 권한일 경우 (모니터링만 가능)
			if(athrt.trim().equals("B4")) {
				List<CodeVO> idtfList = codeService.getCodeListByDvs("L2");	// 부대식별코드, 부대명 불러오기
				model.addAttribute("idtfList", idtfList);
			}
		}	
		
		if(listsearchVO.getDate().equals("")) {
			model.addAttribute("curYear", DateUtil.getCurrentYear());
			model.addAttribute("curMon", DateUtil.getCurrentMonth());
			model.addAttribute("curDay", DateUtil.getCurrentDay());			
		}
		else {	// 검색기능 사용했을 경우
			model.addAttribute("curYear", listsearchVO.getDate().substring(0, 4));
			
			if(listsearchVO.getDate().substring(4, 5).equals("0"))
				model.addAttribute("curMon", listsearchVO.getDate().substring(5, 6));
			else
				model.addAttribute("curMon", listsearchVO.getDate().substring(4, 6));
			
			if(listsearchVO.getDate().substring(6, 7).equals("0"))
				model.addAttribute("curDay", listsearchVO.getDate().substring(7, 8));
			else
				model.addAttribute("curDay", listsearchVO.getDate().substring(6, 8));
			
			model.addAttribute("curIdtf", listsearchVO.getIncdt_idtf_cd());
		}
		
		return "./main/TroopsMonitoring";
	}

   //과업부여
   @RequestMapping(value="AssignTask.do")
   public String AssignTask(	@ModelAttribute("taskVO") TaskVO taskVO,
		   											@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
		   											@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
		   											@ModelAttribute("memberVO") MemberVO memberVO,
		   											HttpServletRequest request,
		   											ModelMap model,
		   											HttpSession httpSession
		   ) throws Exception
   {
				if (httpSession.getAttribute("loginedUser") == null) {
					return "redirect:/login.do";
				}
	   request.setCharacterEncoding("utf-8");
	   String Name = request.getParameter("Name");
	   String Date = "";
	   Date = request.getParameter("Date");
	   Calendar oCalendar = Calendar.getInstance( );
	   if(Date == "" || Date == null)
	   {
		   int Year = oCalendar.get(Calendar.YEAR);
		   int Month = oCalendar.get(Calendar.MONTH) + 1;
		   int Day = oCalendar.get(Calendar.DAY_OF_MONTH); 
		   Date = Year + "-" +Month+"-"+Day;
		   
		   SimpleDateFormat realDate = new SimpleDateFormat("yyyy-MM-dd");
		  
		  Date  realDate1 = realDate.parse(Date);

		   Date = realDate.format(realDate1);

	   }
	   
	   membersearchVO.setSearchCondition1(Name);
	   membersearchVO.setSearchCondition2(Date);
	   membersearchVO.setSearchCode(loginedUser.getIncdt_idtf_cd().trim());
	   
	   List<TaskVO> taskList = taskService.getTaskList(membersearchVO);
	   model.addAttribute("taskList", taskList);
	   
	   List<CheckCodeVO> checkCodeList = checkCodeService.getCheckList(membersearchVO);
	   model.addAttribute("checkCodeList", checkCodeList);
	   
	   model.addAttribute("membersearchVO", membersearchVO);
	   
	   return "./main/AssignTask";
   }
   @RequestMapping(value="AssignTask_insert.do")
   public String AssignTaskInsert(
		   @ModelAttribute("memberVO") MemberVO memberVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO,
		   HttpServletRequest request, 
		   ModelMap model
		   ) throws Exception {
		request.setCharacterEncoding("utf-8");
		String Name = request.getParameter("Name");
		String Date = request.getParameter("Date");
		membersearchVO.setSearchCode(loginedUser.getIncdt_idtf_cd().trim());

		List<MemberVO> memberList = memberService.getMemberList(membersearchVO);

		model.addAttribute("Name", Name);
		model.addAttribute("Date", Date);
		model.addAttribute("memberList", memberList);

		return "./main/AssignTask_insert";
	}

	// 입력 완료 창
	@RequestMapping(value = "Task_Insert_Result.do")
	public String TaskResult(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("taskVO") TaskVO taskVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
			HttpServletRequest request, 
			ModelMap model
			) throws Exception {
		HttpSession httpsession 	= request.getSession();
		String 	incdt_idtf_cd 		=loginedUser.getIncdt_idtf_cd().trim();
		String 	incdt_actvt_type_cd = request.getParameter("INCDT_ACTVT_TYPE_CD");
		String 	actvt_date 			= request.getParameter("ACTVT_DATE");
		String 	task 				= request.getParameter("Task");
		String 	task_psnchnrg_srvno = request.getParameter("TASK_PSNCHNRG_SRVNO");
		String 	seq	 				= request.getParameter("taskSeq");
		Integer Seq					= Integer.parseInt(seq);
		
		taskVO.setIncdt_idtf_cd(incdt_idtf_cd);
		taskVO.setIncdt_actvt_type_cd(incdt_actvt_type_cd);
		taskVO.setActvt_date(actvt_date);
		taskVO.setTask(task);
		taskVO.setTask_psnchnrg_srvno(task_psnchnrg_srvno);
		taskVO.setSeq(seq);
		
		List<TaskVO> taskoverlap = taskService.getTaskSelected(taskVO);
		
		model.addAttribute("membersearchVO", membersearchVO);
		
		if(taskoverlap.size() > 0)
		{
			model.addAttribute("returnUrl", "AssignTask.do?Name="+incdt_actvt_type_cd);
			model.addAttribute("RST", "TaskInsertFailed");			
		}
		else
		{
		taskService.InsertTask(taskVO);
			
		model.addAttribute("returnUrl", "AssignTask.do?Name="+incdt_actvt_type_cd);
		model.addAttribute("RST", "TaskInsertOK");
		}	 
		return "message";
		
	}
   //지휘관지침 입력
   @RequestMapping(value="EnterCommanderGuide.do")
   public String EnterCommanderGuide(@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
		   		@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   		@ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
				HttpServletRequest request,
				ModelMap model,
				HttpSession httpSession
					) throws Exception
					{
					if (httpSession.getAttribute("loginedUser") == null) {
					return "redirect:/login.do";
					}
				   request.setCharacterEncoding("utf-8");
				   String Name = request.getParameter("Name");
				   String Date = "";
				   Date = request.getParameter("Date");
				   String Date_test = request.getParameter("amp;amp;Date");
				   if(Date_test != null)
				   {
					   Date = Date_test;
				   }
//				   Calendar oCalendar = Calendar.getInstance( );
//				   if(Date == "" || Date == null)
//				   {
//					   int Year = oCalendar.get(Calendar.YEAR);
//					   int Month = oCalendar.get(Calendar.MONTH) + 1;
//					   int Day = oCalendar.get(Calendar.DAY_OF_MONTH); 
//					   Date = Year + "-" +Month+"-"+Day;
//					   
//					   SimpleDateFormat realDate = new SimpleDateFormat("yyyy-MM-dd");
//					  
//					   Date realDate1 = realDate.parse(Date);
//
//					   Date = realDate.format(realDate1);
//
//				   }
				   
				   membersearchVO.setSearchCondition1(Name);
				   membersearchVO.setSearchCondition2(Date);
				   System.out.println("Name : " + Name);
				   System.out.println("Date : " + Date);
				   
				   List<ActivityVO> selectActivity = null;
				   List<TaskDataVO> taskData = null;
						   
				   if(Name != null && !(Name.equals("")) && Date != null && !(Date.equals(""))) {
					   selectActivity = activityService.getActivitySelect(membersearchVO);
					   
				   }		
				   System.out.println("act : " + selectActivity);
				   
				   if(selectActivity != null) {
					   taskData = taskDataService.getTaskDataListById(selectActivity.get(0).getId());
				   }
				   
				   System.out.println("taskData : " + taskData);
				   
				   if(taskData != null)
					   model.addAttribute("taskData", taskData.get(0));
				   else
					   model.addAttribute("taskData", null);
				   
				   System.out.println("mod taskData");
	   			List<CtlgCodeVO> ActivityList = ctlgCodeService.getActivityCode(ctlgcodeVO);
	   			model.addAttribute("ActivityList", ActivityList);
				
				model.addAttribute("membersearchVO", membersearchVO);
				
				
	   return "./main/EnterCommanderGuide";
   }
   @RequestMapping(value="EnterCommanderGuide_Modify1.do")
   public String CommanderGuideModify1(@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
		   HttpServletRequest request,
		   ModelMap model
		   ) throws Exception
		   {
	   			request.setCharacterEncoding("utf-8");
	   				String Name = request.getParameter("Name");
	   				String Date = request.getParameter("Date");
	   				String guidnc_1 = request.getParameter("guidnc_1");
	   				
	   				String id = request.getParameter("id");
	   				System.out.println("id : " + id);
	   				
	   				TaskDataVO taskDataVO = new TaskDataVO();
	   				taskDataVO.setGuidnc_1(guidnc_1);
	   				taskDataVO.setId(id);
	   				
	   				taskDataService.updateCommanderGuide1_ById(taskDataVO);
				   
				   model.addAttribute(activityVO);
				   
					 model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name+"&Date="+Date);
					 model.addAttribute("RST", "CommandModifyOK");
					 return "message";
		   }
   @RequestMapping(value="EnterCommanderGuide_Modify2.do")
   public String CommanderGuideModify2(@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
		   HttpServletRequest request,
		   ModelMap model
		   ) throws Exception
		   {
	   			request.setCharacterEncoding("utf-8");
	   				String Name = request.getParameter("Name");
	   				String Date = request.getParameter("Date");
	   				String guidnc_2 = request.getParameter("guidnc_2");

	   				String id = request.getParameter("id");
	   				System.out.println("id : " + id);
	   				
	   				TaskDataVO taskDataVO = new TaskDataVO();
	   				taskDataVO.setGuidnc_2(guidnc_2);
	   				taskDataVO.setId(id);
	   				
	   				taskDataService.updateCommanderGuide2_ById(taskDataVO);
				   
				   model.addAttribute(activityVO);
				   
					 model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name+"&Date="+Date);
					 model.addAttribute("RST", "CommandModifyOK");
					 return "message";
		   }
   @RequestMapping(value="EnterCommanderGuide_Modify3.do")
   public String CommanderGuideModify3(@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
		   HttpServletRequest request,
		   ModelMap model
		   ) throws Exception
		   {
	   			request.setCharacterEncoding("utf-8");
	   				String Name = request.getParameter("Name");
	   				String Date = request.getParameter("Date");
	   				String guidnc_3 = request.getParameter("guidnc_3");

	   				String id = request.getParameter("id");
	   				System.out.println("id : " + id);
	   				
	   				TaskDataVO taskDataVO = new TaskDataVO();
	   				taskDataVO.setGuidnc_3(guidnc_3);
	   				taskDataVO.setId(id);
	   				
	   				taskDataService.updateCommanderGuide3_ById(taskDataVO);
				   
				   model.addAttribute(activityVO);
				   
					 model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name+"&Date="+Date);
					 model.addAttribute("RST", "CommandModifyOK");
					 return "message";
		   }
   @RequestMapping(value="EnderCommanderGuide_Delete1.do")
   public String CommanderGuideDelete1(@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
		   HttpServletRequest request,
		   ModelMap model
		   ) throws Exception
		   {
	   			request.setCharacterEncoding("utf-8");
	   				String Name = request.getParameter("Name");
	   				String Date = request.getParameter("Date");
	   				String guidnc_1 = "";

	   				String id = request.getParameter("id");
	   				System.out.println("id : " + id);
	   				
	   				TaskDataVO taskDataVO = new TaskDataVO();
	   				taskDataVO.setGuidnc_1(guidnc_1);
	   				taskDataVO.setId(id);
	   				
	   				taskDataService.updateCommanderGuide1_ById(taskDataVO);
				   
				   model.addAttribute(activityVO);
				   
					 model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name + "&Date="+Date);
					 model.addAttribute("RST", "CommandModifyOK");
					 return "message";
		   }
   @RequestMapping(value="EnderCommanderGuide_Delete2.do")
   public String CommanderGuideDelete2(@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
		   HttpServletRequest request,
		   ModelMap model
		   ) throws Exception
		   {
	   			request.setCharacterEncoding("utf-8");
	   				String Name = request.getParameter("Name");
	   				String Date = request.getParameter("Date");
	   				String guidnc_2 = "";

	   				String id = request.getParameter("id");
	   				System.out.println("id : " + id);
	   				
	   				TaskDataVO taskDataVO = new TaskDataVO();
	   				taskDataVO.setGuidnc_2(guidnc_2);
	   				taskDataVO.setId(id);
	   				
	   				taskDataService.updateCommanderGuide2_ById(taskDataVO);
				   
				   model.addAttribute(activityVO);
				   
					 model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name + "&Date="+Date);
					 model.addAttribute("RST", "CommandModifyOK");
					 return "message";
		   }
   @RequestMapping(value="EnderCommanderGuide_Delete3.do")
   public String CommanderGuideDelete3(@ModelAttribute("ActivityVO") ActivityVO activityVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("CtlgCodeVO") CtlgCodeVO ctlgcodeVO,
		   HttpServletRequest request,
		   ModelMap model
		   ) throws Exception
		   {
	   			request.setCharacterEncoding("utf-8");
	   				String Name = request.getParameter("Name");
	   				String Date = request.getParameter("Date");
	   				String guidnc_3 = "";

	   				String id = request.getParameter("id");
	   				System.out.println("id : " + id);
	   				
	   				TaskDataVO taskDataVO = new TaskDataVO();
	   				taskDataVO.setGuidnc_3(guidnc_3);
	   				taskDataVO.setId(id);
	   				
	   				taskDataService.updateCommanderGuide3_ById(taskDataVO);
				   
				   model.addAttribute(activityVO);
				   
					 model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name + "&Date="+Date);
					 model.addAttribute("RST", "CommandModifyOK");
					 return "message";
		   }
   
   //긴급승인검토
   @RequestMapping(value="CheckApproval.do")
   public String CheckApproval(
		   		@ModelAttribute("CheckApprovalVO") CheckApprovalVO checkApprovalVO,
		   		@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
		   		HttpServletRequest request,
		   		ModelMap model,
				HttpSession httpSession
					) throws Exception
					{
					if (httpSession.getAttribute("loginedUser") == null) {
					return "redirect:/login.do";
					}
			membersearchVO.setSearchCode(loginedUser.getIncdt_idtf_cd().trim());
			List<CheckApprovalVO> ApprovalList = checkApprovalService.getCheckApprovalList(membersearchVO);
			
			model.addAttribute("ApprovalList", ApprovalList);
			   
				   return "./main/CheckApproval";
			  }
   
   @RequestMapping(value="CheckApprovalResult.do")
   public String CheckApprovalResult(
		   	@ModelAttribute("CheckApprovalVO") CheckApprovalVO checkApprovalVO,
	   		@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
	   		HttpServletRequest request,
	   		ModelMap model
		   ) throws Exception{
	   request.setCharacterEncoding("utf-8");
	   String seq = request.getParameter("seq");
	   
	   model.addAttribute("seq", seq);
	   
	   checkApprovalVO = checkApprovalService.findApproveByKey(checkApprovalVO);
	   model.addAttribute("checkApprovalVO", checkApprovalVO);
	   
	   List<CheckApprovalVO> ApprovalSelect = checkApprovalService.getApprovalSelect(checkApprovalVO);
	   
	   
	   model.addAttribute("Selected", ApprovalSelect);
	   
	   return "./main/CheckApprovalResult";
   }
   
   @RequestMapping(value="CheckApprovlaResult_Result.do")
   public String CheckApprovalResultResult(
		   @ModelAttribute("CheckApprovalVO") CheckApprovalVO checkApprovalVO,
		   HttpServletRequest request,
		   ModelMap model)
   throws Exception{
	   
	   request.setCharacterEncoding("utf-8");
	   String state_cd = request.getParameter("state_cd");
	   String seq = request.getParameter("seq");
	   String opn = request.getParameter("opn");
	   
	   // add 190331	   
	   checkApprovalVO.setSeq(seq);
	   checkApprovalVO.setopn(opn);
	   checkApprovalVO = checkApprovalService.findApproveByKey(checkApprovalVO);
	   
	   checkApprovalVO.setState_cd(state_cd);
	   
	   checkApprovalService.ApproveModify(checkApprovalVO);
	   
	   checkApprovalVO = checkApprovalService.changetoCode(checkApprovalVO);
	   
	   System.out.println("date : " + checkApprovalVO.getActvt_date());
	   System.out.println("id : " + checkApprovalVO.getId());
	   System.out.println("srvno : " + checkApprovalVO.getRqstr_srvno());
	   
	   TaskDataVO forSearchVO = new TaskDataVO();
	   forSearchVO.setId(checkApprovalVO.getId());
	   forSearchVO.setTask_psnchnrg_srvno(checkApprovalVO.getRqstr_srvno());
	   TaskDataVO taskData = taskDataService.getTaskDataVO(forSearchVO);
	   
	   System.out.println("data vo : " + taskData);
	   
	   if(taskData.getCtlg_itm_cd_1() != null) {
		   if(taskData.getCtlg_itm_cd_1().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_1("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_2() != null) {
		   if(taskData.getCtlg_itm_cd_2().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_2("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_3() != null) {
		   if(taskData.getCtlg_itm_cd_3().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_3("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_4() != null) {
		   if(taskData.getCtlg_itm_cd_4().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_4("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_5() != null) {
		   if(taskData.getCtlg_itm_cd_5().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_5("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_6() != null) {
		   if(taskData.getCtlg_itm_cd_6().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_6("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_7() != null) {
		   if(taskData.getCtlg_itm_cd_7().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_7("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_8() != null) {
		   if(taskData.getCtlg_itm_cd_8().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_8("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_9() != null) {
		   if(taskData.getCtlg_itm_cd_9().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_9("N ");
	   }
	   else if(taskData.getCtlg_itm_cd_10() != null) {
		   if(taskData.getCtlg_itm_cd_10().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_10("N ");
	   } 
		
	   taskDataService.updateYNState(taskData);
	   /////////////////////////////////////	   
	   
	   model.addAttribute(checkApprovalVO);
	   
		 model.addAttribute("returnUrl", "CheckApproval.do");
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
	   
   }
		   
   
   //수시 안전관리활동 검토
   @RequestMapping(value="CheckOccasionalSafeManagement.do")
   public String CheckOccasionalSafeManagement(
		   @ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
		   HttpServletRequest request,
		   ModelMap model) throws Exception
   {
	   
	   requestActivityVO.setRqstr_srvno(loginedUser.getIncdt_idtf_cd().trim());
	   List<RequestActivityVO> RequestList = requestActivityService.getRequestList(requestActivityVO);
	  
	   model.addAttribute(requestActivityVO);
	   model.addAttribute("RequestList", RequestList);
	   
	   return "./main/CheckOccasionalSafeManagement";
   }
   @RequestMapping(value="CheckOccasionalSafeManagement_Select.do")
   public String CheckOccasionalSafeManagementSelect(
		   @ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
		   HttpServletRequest request,
		   ModelMap model) throws Exception
   {
	   String seq = request.getParameter("seq");
	   requestActivityVO.setSeq(seq);
	   List<RequestActivityVO> RequestList = requestActivityService.getRequestList(requestActivityVO);
	   requestActivityVO.setState_cd(RequestList.get(0).state_cd);
	   model.addAttribute(requestActivityVO);
	   model.addAttribute("RequestList", RequestList);
	   
	   return "/main/CheckOccasionalSafeManagement_Select";
   }
   
   @RequestMapping(value="CheckOccasionalSelect_Result.do")
   public String CheckOccasionalSelect_Result(
		   @ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
		   HttpServletRequest request,
		   ModelMap model) throws Exception
   {
	   String seq = request.getParameter("seq");
	   String state_cd = request.getParameter("state_cd");
	   String opn = request.getParameter("opn");
	   requestActivityVO.setSeq(seq);
	   requestActivityVO.setopn(opn);
	   requestActivityVO.setState_cd(state_cd);
	   
	   requestActivityService.RequestActivityResult(requestActivityVO);
	   model.addAttribute(requestActivityVO);
	   
		 model.addAttribute("returnUrl", "CheckOccasionalSafeManagement.do");
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
   }
   
   //사용자관리
   @RequestMapping(value="ManageUser.do")
   public String ManageUser() throws Exception
   {
	   return "./main/ManageUser";
   }
   //사용자관리 결과
   @RequestMapping(value="ModifyResult.do")
   public String ModifyResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "./main/UserList";
	}

	// 사용자 정보
	@RequestMapping(value = "/Userlist.do")
	public String selectMemberList(
			@ModelAttribute("memberVO") MemberVO memberVO,
			@ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
			String flag,
	   		ModelMap model,
			HttpSession httpSession,
			HttpServletRequest request
				) throws Exception
				{
				if (httpSession.getAttribute("loginedUser") == null) {
				return "redirect:/login.do";
				}
				
					membersearchVO.setSearchCode(loginedUser.getIncdt_idtf_cd().trim());
					String PageIndex = request.getParameter("pageIndex");
					if(PageIndex != null)
					{
					int to = Integer.parseInt(PageIndex);
					membersearchVO.setPageIndex(to);
					}
					// Paging Setting
					int totCnt = memberService.getMemberListTotCnt(membersearchVO);
					PaginationInfo paginationInfo = new PaginationInfo();
					paginationInfo.setTotalRecordCount(totCnt);
					
					paginationInfo.setCurrentPageNo(membersearchVO.getPageIndex());
					paginationInfo.setRecordCountPerPage(membersearchVO.getRecordCountPerPage());
					paginationInfo.setPageSize(membersearchVO.getPageSize());
					
					membersearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
					membersearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
					membersearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
					
					int start = (membersearchVO.getPageIndex() * paginationInfo.getRecordCountPerPage())+1;
					int end = start-(paginationInfo.getRecordCountPerPage()-1);
					
					membersearchVO.setStart(start);
					membersearchVO.setEnd(end);
					
					List<MemberVO> memberList = memberService.getMemberList(membersearchVO);
					
					model.addAttribute("paginationInfo", paginationInfo);
					model.addAttribute("pageIndex", membersearchVO.getPageIndex());
					model.addAttribute("memberList", memberList);

		return "./main/UserList";
	}

	// 사용자 추가
	@RequestMapping(value = "/member_insert.do")
	public String InsertUser(HttpServletRequest request) throws Exception {
		HttpSession httpsession = request.getSession();
		if (null != httpsession.getAttribute("SS_SN")) {
			return "./main/MemberInsert";
		} else {
			// return "redirect:/Userlist.do";
			return "./main/MemberInsert";
		}

	}

	@RequestMapping(value = "Member_Insert_Result.do")
	public String InsertResult(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession httpsession = request.getSession();
		if (null != httpsession.getAttribute("SS_SN")) {
			// MemberVO mvVO = new MemberVO();
			memberVO.setSrvno(request.getParameter("srvno"));
			memberVO.setPw(request.getParameter("password"));
			memberVO.setRnkcd(request.getParameter("rnkcd"));
			memberVO.setStmt(request.getParameter("stmt"));
			memberVO.setRspofc_nm((String) request.getParameter("rspofc_nm"));
			memberVO.setathrt((String) request.getParameter("athrt"));
			memberVO.setPrtbl_telno((String) request	.getParameter("prtbl_telno"));
			memberVO.setIncdt_idtf_cd((String) request.getParameter("incdt_idtf_cd"));
			memberVO.setmontr((String) request	.getParameter("montr"));

			memberService.insertMember(memberVO);
			model.addAttribute("membersearchVO", membersearchVO);

			model.addAttribute("returnUrl", "Userlist.do");
			model.addAttribute("RST", "InsertOK");
			return "message";
		} else {
			// 로그인 기능 미구현으로 인한 대체
			memberVO.setSrvno(request.getParameter("srvno"));
			memberVO.setPw(request.getParameter("password"));
			memberVO.setRnkcd(request.getParameter("rnkcd"));
			memberVO.setStmt(request.getParameter("stmt"));
			memberVO.setRspofc_nm(request.getParameter("rspofc_nm"));
			memberVO.setathrt(request.getParameter("athrt"));
			memberVO.setPrtbl_telno(request.getParameter("prtbl_telno"));
			memberVO.setIncdt_idtf_cd(request.getParameter("incdt_idtf_cd"));
			memberVO.setmontr(request.getParameter("montr"));
			

			memberService.insertMember(memberVO);
			// 기능 구현시 제거

			// return "./main/UserList";
			model.addAttribute("returnUrl", "Userlist.do");
			model.addAttribute("RST", "InsertOK");
			return "message";
		}
	}

	// 사용자 수정
	@RequestMapping(value = "/member_modify.do")
	public String ModifyUser
					(
							@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
							@ModelAttribute("memberVO") MemberVO memberVO,
							HttpServletRequest request, 
							ModelMap model
					) 
							throws Exception
					{
		 				request.setCharacterEncoding("utf-8");
		   				String srvno = request.getParameter("srvno");
		   				memberVO.setSrvno(srvno);
		   				
		   				List<MemberVO> memberselect =memberService.getMemberselect(memberVO); 
		   				
		   				System.out.println(memberselect.get(0).getathrt());
		   				System.out.println(memberselect.get(0).getmontr());
		   						
		   				model.addAttribute("memberselect", memberselect);
		   				
		   				
						return "./main/MemberModify";
					}
	@RequestMapping(value="/MemberModify_result.do")
	public String ModifyUserResult
	(		
			@ModelAttribute("memberVO") MemberVO memberVO,
			@ModelAttribute("userVO") UserVO userVO,
			HttpServletRequest request,
			ModelMap model
			)
	throws Exception
	{
		request.setCharacterEncoding("utf-8");

		memberVO.setSrvno(request.getParameter("srvno"));
		memberVO.setPw(request.getParameter("password"));
		memberVO.setRnkcd(request.getParameter("rnkcd"));
		memberVO.setStmt(request.getParameter("stmt"));
		memberVO.setRspofc_nm(request.getParameter("rspofc_nm"));
		memberVO.setathrt(request.getParameter("athrt"));
		memberVO.setPrtbl_telno(request.getParameter("prtbl_telno"));
		memberVO.setIncdt_idtf_cd(request.getParameter("incdt_idtf_cd"));
		memberVO.setmontr(request.getParameter("montr"));
		
		memberService.ModifyMember(memberVO);
		 
		model.addAttribute("returnUrl", "Userlist.do");
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
	}
	//사용자 삭제
	@RequestMapping(value = "/DeleteMember.do")
	public String DeleteUser(
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession httpsession = request.getSession();
		if (null != httpsession.getAttribute("SS_SN")) {
			memberVO.setSrvno(request.getParameter("srvno"));

			memberService.DeleteMember(memberVO);
			
			 model.addAttribute("returnUrl", "Userlist.do");
			 model.addAttribute("RST", "DeleteOK");
			 return "message";
		}
		else
		{
			if(request.getParameter("srvno") == null)
			{
				return null;
			}
			//로그인 기능 미구현으로 인한 대체
			memberVO.setSrvno				(request.getParameter("srvno"));
			memberService.DeleteMember(memberVO);
			// 기능 구현시 제거

			// return "./main/UserList";
			model.addAttribute("returnUrl", "Userlist.do");
			model.addAttribute("RST", "DeleteOK");
			return "message";
		}
	}
   //체크리스트항목 선별
   @RequestMapping(value="SelectCheckListItem.do")
   public String SelectCheckListItem(
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO,
		   HttpServletRequest request,
		   ModelMap model,
		   HttpSession httpSession
			) throws Exception
			{
			if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
			}
		request.setCharacterEncoding("utf-8");
	   String ctlg_cd = request.getParameter("ctlg_cd");

	   checklistVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
	   checklistItemVO.setCtlg_cd(ctlg_cd);
	   checklistVO.setCtlg_cd(ctlg_cd);
	   
	   sptlyCodeVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
	   sptlyCodeVO.setCtlg_cd(ctlg_cd);
	   List<SptlyCodeVO> ctlgList = sptlyCodeService.ctlgReset(sptlyCodeVO);
	   if(ctlgList.size() >= 1)
	   {
	   String ctlg_cd_real = ctlgList.get(0).getCtlg_cd().trim();
	   sptlyCodeVO.setCtlg_cd(ctlg_cd_real); 
	   }
	   List<ChecklistItemVO> Checklist = checklistItemService.ChecklistSelect(checklistItemVO);
	   List<ChecklistItemVO> CheckSelect = checklistService.getChecklist(checklistVO);
	   List<SptlyCodeVO> taskDataList = sptlyCodeService.getCtlgDataList(sptlyCodeVO);
	   //prtcuse_frqc
	   
	   int length = Checklist.size();
	   for(int i = 0; i<length; i++){
		   	int prtcuse_string = Checklist.get(i).prtcuse_frqc;
			   		
			String to  = Integer.toString(prtcuse_string);
			Checklist.get(i).prtcust_frqc_String = to;
			Checklist.get(i).prtcuse_frqc = prtcuse_string;
	   		}	   	
	   int lengthSelect = CheckSelect.size();
	   
	   for(int i = 0; i<lengthSelect; i++){
	   		int prtcuse_string = CheckSelect.get(i).prtcuse_frqc;

	   		String to  = Integer.toString(prtcuse_string);
	   		CheckSelect.get(i).prtcust_frqc_String = to;
	   		CheckSelect.get(i).prtcuse_frqc = prtcuse_string;
		   }

	   
	   model.addAttribute("taskDataList", taskDataList);
	   model.addAttribute("checklistItemVO", checklistItemVO);
	   model.addAttribute("Checklist", Checklist);
	   model.addAttribute("CheckSelect", CheckSelect);
	   
	   return "./main/SelectCheckListItem";
   }
   @SuppressWarnings("unchecked")
@RequestMapping(value="SelectChecklistItem_ajax.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String SelectChecklistAjax(
		   HttpServletRequest request, HttpServletResponse response, Model model,
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO
		   )throws Exception
   {
	   String incdt = loginedUser.getIncdt_idtf_cd().trim();
	   String seq = request.getParameter("code");
	   String jsonString = request.getParameter("jsonString");
	   String Task = request.getParameter("Task");
	   jsonString = jsonString.replaceAll("&quot;", "\"");
	   
	   JSONParser parser = new JSONParser();
	   JSONObject json = (JSONObject) parser.parse(jsonString);
	   
	   sptlyCodeVO.setTask_cd(seq);
	   sptlyCodeVO.setIncdt_idtf_cd(incdt);
	   sptlyCodeVO.setCtlg_cd(Task);
	   
	   List<SptlyCodeVO>TaskSearched = sptlyCodeService.getActivityCtlgList(sptlyCodeVO);
	   JSONArray jaTask = new JSONArray();
	   if(TaskSearched.size() > 0)
	   {
		   String[] ctlg = new String[10];
		   String[] ctlg_nm = new String[10];
		   
		   ctlg[0] 	= TaskSearched.get(0).getCtlg_itm_cd_1();
		   ctlg[1] 	= TaskSearched.get(0).getCtlg_itm_cd_2();
		   ctlg[2] 	= TaskSearched.get(0).getCtlg_itm_cd_3();
		   ctlg[3] 	= TaskSearched.get(0).getCtlg_itm_cd_4();
		   ctlg[4] 	= TaskSearched.get(0).getCtlg_itm_cd_5();
		   ctlg[5] 	= TaskSearched.get(0).getCtlg_itm_cd_6();
		   ctlg[6] 	= TaskSearched.get(0).getCtlg_itm_cd_7();
		   ctlg[7] 	= TaskSearched.get(0).getCtlg_itm_cd_8();
		   ctlg[8] 	= TaskSearched.get(0).getCtlg_itm_cd_9();
		   ctlg[9] 	= TaskSearched.get(0).getCtlg_itm_cd_10();
		   
		   int length = (ctlg.length);
		   
		   for(int i=0;i<length;i++)
		   {
			   JSONObject joTask = new JSONObject();
			   
			   checklistItemVO.setCtlg_itm_cd(ctlg[i]);
			   String cltg_test = checklistItemVO.getCtlg_cd();
			   List<ChecklistItemVO> PreSearched = checklistItemService.ChecklistPreSelect(checklistItemVO);
			   if(PreSearched.size() > 0)
			   {
			   joTask.put("ctlg_cd", PreSearched.get(0).ctlg_cd);
			   joTask.put("ctlg_itm_cd", PreSearched.get(0).ctlg_itm_cd);
			   joTask.put("ctlg_itm_ctnt", PreSearched.get(0).ctlg_itm_ctnt);
			   joTask.put("stdd_yn", PreSearched.get(0).stdd_yn);
			   joTask.put("prtcuse_frqc", PreSearched.get(0).prtcuse_frqc);
	
			   jaTask.add(joTask);
			   }
			   else
			   {
				   
			   }
		   }
	   }
	   String jsonResult = jaTask.toString();
	   
	   return jsonResult;
   }
   @RequestMapping(value="SelectCheckListResult.do")
   public String SelectCheckListResult(
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO,
		   HttpServletRequest request,
		   ModelMap model,
		   HttpSession httpSession
			) throws Exception
			{
			if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
			}
	   String name = request.getParameter("ctlg_cd");
	   String code = request.getParameter("code");
	   String seq = "123";
	   Integer Realseq = Integer.parseInt(seq); 
	   String Reset = "";
	   code = code.replaceFirst("/","");
	   String[] code2 = null;
	   String[] code3 = null;
	   code3 = new String[10];
	   code2 = code.split("/");

	   for(int i = 0;i<code2.length;i++)
	   {
		   code3[i] = code2[i];
	   }
	   
	   Reset = request.getParameter("Reset");
	   
	   checklistItemVO.setCtlg_cd(name);
	   checklistVO.setCtlg_cd(name);
	   checklistVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
	   checklistService.Checklistreset(checklistVO);
	   
	   sptlyCodeVO.setCtlg_cd(name);
	   sptlyCodeVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
	   sptlyCodeVO.setSeq(Realseq);
	   if(Reset =="" || Reset == null){
		   	for (String num : code3)
		   	{
		   		checklistItemVO.setCtlg_itm_cd(num);
		   		checklistItemService.ChecklistUpdate(checklistItemVO);
		   	}
		   	checklistVO.setCtlg_itm_cd_1(code3[0]);
		   	checklistVO.setCtlg_itm_cd_2(code3[1]);
		   	checklistVO.setCtlg_itm_cd_3(code3[2]);
		   	checklistVO.setCtlg_itm_cd_4(code3[3]);
		   	checklistVO.setCtlg_itm_cd_5(code3[4]);
		   	checklistVO.setCtlg_itm_cd_6(code3[5]);
		   	checklistVO.setCtlg_itm_cd_7(code3[6]);
		   	checklistVO.setCtlg_itm_cd_8(code3[7]);
		   	checklistVO.setCtlg_itm_cd_9(code3[8]);
		   	checklistVO.setCtlg_itm_cd_10(code3[9]);
	   
	   checklistService.ChecklistSelect_Result(checklistVO);
	   }
	   
		model.addAttribute("returnUrl", "SelectCheckListItem.do?ctlg_cd="+name);
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
		   }
   
   @RequestMapping(value="SelectCheckListPreSetCreate.do")
   public String SelectCheckListPreSetCreate(
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO,
		   HttpServletRequest request,
		   ModelMap model,
		   HttpSession httpSession
			) throws Exception
			{
			if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
			}
	   String name = request.getParameter("ctlg_cd");
	   String code = request.getParameter("code");
	   String task = request.getParameter("task");
	   String srvno = loginedUser.getSrvno();
	   String incdt = loginedUser.getIncdt_idtf_cd().trim();
	   String Task_seq = request.getParameter("Task_seq");
	   String Task_nm = request.getParameter("Task_nm");
			   
	   String Reset = "";
	   code = code.replaceFirst("/","");
	   String[] code2 = null;
	   String[] code3 = null;
	   code3 = new String[10];
	   code2 = code.split("/");

	   for(int i = 0;i<code2.length;i++)
	   {
		   code3[i] = code2[i];
	   }
	   
	   Reset = request.getParameter("Reset");
	   
	   sptlyCodeVO.setTask_cd(Task_seq);
	   sptlyCodeVO.setIncdt_idtf_cd(incdt);

	   List<SptlyCodeVO> searchif = sptlyCodeService.getActivityCtlgList(sptlyCodeVO);
	   if(searchif.size() > 0)
	   {
		   sptlyCodeVO.setTask(Task_nm);
		   sptlyCodeVO.setTask_cd(searchif.get(0).getTask_cd());
		   sptlyCodeVO.setSeq(searchif.get(0).getSeq());
		   
	   }
	   else
	   {
		   sptlyCodeVO.setTask(Task_nm);
		   sptlyCodeVO.setTask_cd(Task_seq);
	   }
	   sptlyCodeVO.setCtlg_cd(name);
	   sptlyCodeVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
	   sptlyCodeService.Checklistreset(sptlyCodeVO);
	   List<SptlyCodeVO> searchNew = sptlyCodeService.getActivityCtlgList(sptlyCodeVO);
	   sptlyCodeVO.setSeq(searchNew.get(0).getSeq());
		   	for (String num : code3)
		   	{
		   		checklistItemVO.setCtlg_itm_cd(num);
		   		checklistItemService.ChecklistUpdate(checklistItemVO);
		   	}
		   	sptlyCodeVO.setCtlg_itm_cd_1(code3[0]);
		   	sptlyCodeVO.setCtlg_itm_cd_2(code3[1]);
		   	sptlyCodeVO.setCtlg_itm_cd_3(code3[2]);
		   	sptlyCodeVO.setCtlg_itm_cd_4(code3[3]);
		   	sptlyCodeVO.setCtlg_itm_cd_5(code3[4]);
		   	sptlyCodeVO.setCtlg_itm_cd_6(code3[5]);
		   	sptlyCodeVO.setCtlg_itm_cd_7(code3[6]);
		   	sptlyCodeVO.setCtlg_itm_cd_8(code3[7]);
		   	sptlyCodeVO.setCtlg_itm_cd_9(code3[8]);
		   	sptlyCodeVO.setCtlg_itm_cd_10(code3[9]);
	   
		   	sptlyCodeService.ChecklistPreset_Update(sptlyCodeVO);
		   	
	   System.out.println(taskDataVO);
		model.addAttribute("returnUrl", "SelectCheckListItem.do?ctlg_cd="+name);
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
		   }
   
   //체크리스트항목 관리
   @RequestMapping(value="ManageCheckListItem.do")
   public String ManageCheckListItem(
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   HttpServletRequest request,
		   ModelMap model,
		   HttpSession httpSession
			) throws Exception
			{
			if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
			}
			request.setCharacterEncoding("utf-8");
		   String ctlg_cd = request.getParameter("ctlg_cd");
		   
		   checklistItemVO.setCtlg_cd(ctlg_cd);
		   
		   List<ChecklistItemVO> Checklist = checklistItemService.ChecklistSelect(checklistItemVO);
		   
		   model.addAttribute("checklistItemVO", checklistItemVO);
		   model.addAttribute("Checklist", Checklist);
		   
	   return "./main/ManageCheckListItem";
   }
   //체크리스트 Test Ping
   
   @SuppressWarnings("unchecked")
@RequestMapping(value="ManageCheckList_Ajax_json.do", produces="text/html; charset=utf-8")
   @ResponseBody //리턴하기 위해 추가
   public String ManageCheckList_Ajax_json(
		   ChecklistItemVO checklistItemVO,
		   HttpServletRequest request, HttpServletResponse response, Model model
		   )
   throws Exception{
	   String ctlg_itm_cd = request.getParameter("ctlg_itm_cd");
		
	   checklistItemVO.setCtlg_itm_cd(ctlg_itm_cd);
	   String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
	   jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
  	
	   JSONParser parser = new JSONParser();
	   JSONObject json = (JSONObject) parser.parse(jsonString); //jsonString을 json객체로 변경
		
	   //json.code1
	   String code = (String) json.get("code1");
	   
	   List<ChecklistItemVO> CheckCreate = requestChecklistService.getChecklist(checklistItemVO);
	   if(CheckCreate.size() < 1)
	   {
		   json.put("code1", "OK");
	   }
	   else
	   {
		   CheckCreate = requestChecklistService.getCheckCreate(checklistItemVO);
		   String lastKeyword;
	       
	       lastKeyword = CheckCreate.get(CheckCreate.size() - 1).ctlg_itm_cd;
	       String[] array = lastKeyword.split("_");
	       String Code = array[0];
	       String CodeNum = array[1];
	       int CodeNew =Integer.parseInt( array[1]) + 1;               
	       String CodeReal  = Code + "_"  + String.valueOf(CodeNew);
	 
	       json.put("code1",CodeReal);
	   }
	   
		
	   String jsonResult = json.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
	   return jsonResult; //ajax의 result로 리턴됨
  }

   
   //체크리스트 항목 입력
   @RequestMapping("ManageCheckListItem_Insert.do")
   public String ManageChecklistItemInsert(
		@ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
		HttpServletRequest request,
		ModelMap model
		   ) throws Exception{
		request.setCharacterEncoding("utf-8");
		 String ctlg_cd = request.getParameter("ctlg_cd");
		 model.addAttribute("ctlg_cd", ctlg_cd);
		 checklistItemVO = checklistItemService.getChecklistCode(checklistItemVO);
		 model.addAttribute("checklistItemVO", checklistItemVO);
		 return "./main/ManageCheckListResult";
   }
   //체크리스트항목 일괄 입력
   @RequestMapping("ManageCheckListItem_Result")
   public String ManageCheckListItem_Result(
		   	@ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
				request.setCharacterEncoding("utf-8");
				String ctlg_cd = request.getParameter("Ctlg_cd");
				String Code = request.getParameter("Code");
				String Name = request.getParameter("Name");
				
				checklistItemVO.setCtlg_cd(ctlg_cd);
				checklistItemVO.setCtlg_itm_cd(Code);
				checklistItemVO.setCtlg_itm_ctnt(Name);
				
				checklistItemService.ChecklistInsert(checklistItemVO);
				
				model.addAttribute("returnUrl", "ManageCheckListItem.do?ctlg_cd="+ctlg_cd);
				model.addAttribute("RST", "ApproveOK");
				
				return "message";
   			}
// input excel
	@RequestMapping(value = "Checklist_Insert_Excel.do")
	public String Checklist_Insert_Excel(
			@ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
			HttpServletRequest request,
			ModelMap model) throws Exception {
		request.setCharacterEncoding("utf-8");
		   String Check=request.getParameter("Check");
		   checklistItemVO.setCtlg_cd(Check);
		   model.addAttribute("checklistItemVO", checklistItemVO);
		   
		return "./main/Checklist_Insert_Excel";
	}
   
	   @SuppressWarnings("static-access")
	@RequestMapping(value = "ChecklistexcelUploadAjax.do", method = RequestMethod.POST)
	    public String ChecklistexcelUploadAjax(MultipartHttpServletRequest request, HttpServletResponse response,
	    		ModelMap model)  throws Exception{
			System.out.println("excel Start");
	        MultipartFile excelFile = request.getFile("excelFile");
	        //HandlerFile handlerFile = new HandlerFile(request);
	        //String filePath = handlerFile.getFilePath();
	        
	        System.out.println("filePath : " + excelFile);
	        if(excelFile==null || excelFile.isEmpty()){
	            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
	        }
	        
	        File destFile = new File("C:\\"+excelFile.getOriginalFilename());
	        excelFile.transferTo(destFile);
	        
	        
	        
	        ExcelReadOption excelReadOption = new ExcelReadOption();
	        excelReadOption.setFilePath(destFile.getAbsolutePath());
	        excelReadOption.setOutputColumns("A","B","C");
	        excelReadOption.setStartRow(1);        
	        
	        List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);        
	        List<ChecklistItemVO> Checklist = new ArrayList<ChecklistItemVO>();
	        ChecklistItemVO RestVO = new ChecklistItemVO();
	        
	        for(Map<String, String> article: excelContent){
	        	
	        	System.out.println(article.get("A"));
	        	System.out.println(article.get("B"));
	            System.out.println(article.get("C"));
	            
	            if((article.get("A") == null || article.get("A").equals(""))
	            		|| (article.get("B") == null || article.get("B").equals(""))
	            		|| (article.get("C") == null || article.get("C").equals(""))) {
	            	scriptWriter.getInstance().printScript(response, "<script>alert('파일 내에 누락된 정보가 있습니다. 파일을 확인해주십시오.'); location.href = 'ManageCheckListItem.do';</script>", true);
	            }
	            ChecklistItemVO codeVO = new ChecklistItemVO();
	            
	            codeVO.setCtlg_cd(article.get("A"));
	            codeVO.setCtlg_itm_cd(article.get("B"));
	            codeVO.setCtlg_itm_ctnt(article.get("C"));
	            
	            RestVO.setCtlg_cd(article.get("A"));
	            RestVO.setCtlg_itm_cd(article.get("B"));
	            RestVO.setCtlg_itm_ctnt(article.get("C"));
	            
	            Checklist.add(codeVO);
	            
	        }
	        
	        checklistItemService.insertCodeListInExcel(Checklist);
	        
	        String Ctlg_cd = RestVO.getCtlg_cd();
	        
			model.addAttribute("returnUrl", "ManageCheckListItem.do?ctlg_cd="+Ctlg_cd);
			 model.addAttribute("RST", "ApproveOK");
			 
			 return "message";
	    }
  
   //요청된 체크리스트 검토

   @RequestMapping(value="CheckRequestedCheckList.do")
   public String CheckRequestedCheckList(
		   @ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO,
	   		@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
	   		HttpServletRequest request,
			   ModelMap model,
			   HttpSession httpSession
				) throws Exception
				{
				if (httpSession.getAttribute("loginedUser") == null) {
				return "redirect:/login.do";
				}
				
		requestChecklistVO.setRqstr_srvno(loginedUser.getIncdt_idtf_cd().trim());
		List<RequestChecklistVO> CheckReuqest = requestChecklistService.getChecklistRequest(requestChecklistVO);
		
		model.addAttribute("CheckReuqest", CheckReuqest);
		
	
	   return "./main/CheckRequestedCheckList";
   }   
   
   @RequestMapping(value="CheckRequest_Result.do")
   public String CheckRequestResult(
           @ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO,
           @ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
           HttpServletRequest request,
           ModelMap model
           )throws Exception{
       String Seq;
       String state_cd;
       String rvw_opn;
       String Approved = ("D2");
       String Denied =("D3");
       String isNew;
       String ctlg_cd;
       String ctlg_itm_ctnt;
       String ctlg_itm_cd_1;
       
       Seq = request.getParameter("seq");
       state_cd = request.getParameter("state_cd");
       rvw_opn = request.getParameter("rvw_opn");
       isNew = request.getParameter("isNew");
       ctlg_cd = request.getParameter("ctlg_cd");
       ctlg_itm_ctnt = request.getParameter("ctlg_itm_ctnt");
       ctlg_itm_cd_1 = request.getParameter("cltg_itm_cd_1");
       
       requestChecklistVO.setSeq(Seq);
       requestChecklistVO.setState_cd(state_cd);
       requestChecklistVO.setRsn(rvw_opn);
       checklistItemVO.setCtlg_cd(ctlg_cd);
       
       if(ctlg_itm_cd_1 ==null)
       {
    	   isNew = "-1";
       }
       
       
       if(state_cd.equals(Approved) ){
           if(isNew.equals("") || isNew.equals("-1")){
               List<ChecklistItemVO> CheckCreate = requestChecklistService.getCheckCreate(checklistItemVO);
               
               String lastKeyword;
               
               lastKeyword = CheckCreate.get(CheckCreate.size() - 1).ctlg_itm_cd;
               String[] array = lastKeyword.split("_");
               String Code = array[0];
               String CodeNum = array[1];
               int CodeNew =Integer.parseInt( array[1]) + 1;               
               String CodeReal  = Code + "_"  + String.valueOf(CodeNew);
               
               checklistItemVO.setCtlg_itm_cd(CodeReal);
               checklistItemVO.setCtlg_itm_ctnt(ctlg_itm_ctnt);
               
               
               requestChecklistService.RequestCreateNew(checklistItemVO);
               requestChecklistService.RequestDeny(requestChecklistVO);
                 model.addAttribute("returnUrl", "CheckRequestedCheckList.do");
                 model.addAttribute("RST", "CommandModifyOK");
                 return "message";
           }
           else{
        	   requestChecklistVO.setCtlg_itm_cd_2(ctlg_itm_ctnt);
        	   requestChecklistVO.setCtlg_itm_cd_1(ctlg_itm_cd_1);
               requestChecklistService.RequestDeny(requestChecklistVO);   
               requestChecklistService.RequestAccept(requestChecklistVO);
               
                 model.addAttribute("returnUrl", "CheckRequestedCheckList.do");
                 model.addAttribute("RST", "CommandModifyOK");
                 return "message";
           }
       }
       else if (state_cd.equals(Denied) ){
           requestChecklistService.RequestDeny(requestChecklistVO);
             model.addAttribute("returnUrl", "CheckRequestedCheckList.do");
             model.addAttribute("RST", "CommandModifyOK");
             return "message";
           
       }
       else{
       return "./main/CheckRequestedCheckList";
       }
   }
   

   //요청된 체크리스트 편집
   @RequestMapping(value="CheckRequestSelect.do")
   public String CheckRequestSelect(
		   @ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO,
	   		@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
	   		HttpServletRequest request,
			   ModelMap model,
			   HttpSession httpSession
				) throws Exception
				{
				if (httpSession.getAttribute("loginedUser") == null) {
				return "redirect:/login.do";
				}
	   		String seq = request.getParameter("seq");
	   		String opn = request.getParameter("rvw_opn");

	   		requestChecklistVO.setSeq(seq);
	   		requestChecklistVO.setOpn(opn);
			List<RequestChecklistVO> CheckReuqest = requestChecklistService.getChecklistRequest(requestChecklistVO);
			requestChecklistVO.setState_cd(CheckReuqest.get(0).state_cd);
			
			model.addAttribute("CheckReuqest", CheckReuqest);
			model.addAttribute("requestChecklistVO", requestChecklistVO);
		
		   return "./main/CheckRequestedCheckSelect";
	   }   
   
	// seungwon
	// seungwon 19.02.12 //
	@SuppressWarnings("static-access")
	@RequestMapping(value = "loginAction.do")
	public String loginAction(final HttpServletResponse response, HttpServletRequest request, HttpSession httpSession) throws Exception {
		System.out.println("login start");
		
		if(httpSession.getAttribute("loginedUser") != null)	
		{
			httpSession.removeAttribute("loginedUser");
		}		
		System.out.println("login addr : "+request.getRemoteAddr());
		System.out.println("login Host : "+request.getRemoteHost());
		String userIp = request.getRemoteAddr();
		String userHostName = request.getRemoteHost();
		
		boolean doLogin = false;
		
		//String formSrvno = (String)httpSession.getAttribute("SS_SN");
		//String formPw = (String)httpSession.getAttribute("SS_PW");
		String formSrvno = request.getParameter("srvno");
		String formPw = request.getParameter("pw");
		System.out.println("srvno : " + formSrvno);
		UserVO tempUserVO = loginService.getUserVO(formSrvno);
		httpSession.setAttribute("SS_RSLT", "0");	// for debug
		if (httpSession.getAttribute("SS_RSLT") != null) {
			if (tempUserVO != null) 
			{
				System.out.println("vo is not null");
				
				if (tempUserVO.getacc_state_info().equals("K2")) 
				{
					scriptWriter.getInstance().printScript(response, "<script>alert('계정이 잠금상테입니다. 관리자에게 문의하십시오.'); location.href = 'login.do';</script>", true);
				}
				else 
				{				
					if (tempUserVO.getPw().equals(formPw)) {
						
						System.out.println("pw is true");
						
						if(tempUserVO.getathrt().trim().equals("B1")) {
							if (loginService.CountChangePwDate(tempUserVO.getSrvno()) >= 30) {
								httpSession.setAttribute("changePwSrv",	tempUserVO.getSrvno());
								scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호를 변경한지 30일이 지났습니다. 비밀번호를 변경해주십시오.'); location.href = 'PasswordChange.do';</script>", true);
							} 
							else {
								loginedUser = tempUserVO;
								
								httpSession.setAttribute("loginedUser", loginedUser); 						// 유저 객체
								httpSession.setAttribute("SS_SN", loginedUser.getSrvno()); 					// 군번
								httpSession.setAttribute("SS_ATHRT", loginedUser.getathrt()); 			// 권한 코드
								httpSession.setAttribute("SS_RNK", loginedUser.getRnkcd_nm()); 				// 계급명
								httpSession.setAttribute("SS_NM", loginedUser.getStmt());					// 이름
								httpSession.setAttribute("SS_IDTF", loginedUser.getIncdt_idtf_cd_nm()); 	// 소속부대
								httpSession.setAttribute("SS_MNT", loginedUser.getmontr());			// 모니터링 권한
								httpSession.setAttribute("SS_YEAR", DateUtil.getCurrentYear()); 			// year
								httpSession.setAttribute("SS_MON", DateUtil.getCurrentMonth()); 			// month
								httpSession.setAttribute("SS_DAY", DateUtil.getCurrentDay()); 				// day						
								
								loginService.InitErrCount(loginedUser);
								loginService.updateLoginDate(loginedUser);
								
								// update login user vo
								loginedUser = loginService.getUserVO(tempUserVO.getSrvno());
								isLogin = true;							

								return "redirect:/index.do";
							}
						}
						else {
							if(tempUserVO.getIp() == null) {
								scriptWriter.getInstance().printScript(response, "<script>alert('IP가 등록되어있지 않은 사용자입니다. 관리자에게 문의하십시오.'); location.href = 'login.do';</script>", true);
							}
							else {
								if( !(tempUserVO.getIp().trim().equals(userIp)) ) {
									System.out.println("ip is false");
									scriptWriter.getInstance().printScript(response, "<script>alert('접속 불가능한 IP입니다. 지정된 PC에서 접속하여야합니다.'); location.href = 'login.do';</script>", true);
								}
								else {
									System.out.println("ip is true");
									if (loginService.CountChangePwDate(tempUserVO.getSrvno()) >= 30) {
										httpSession.setAttribute("changePwSrv",	tempUserVO.getSrvno());
										scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호를 변경한지 30일이 지났습니다. 비밀번호를 변경해주십시오.'); location.href = 'PasswordChange.do';</script>", true);
									} 
									else {
										loginedUser = tempUserVO;
										
										httpSession.setAttribute("loginedUser", loginedUser); 						// 유저 객체
										httpSession.setAttribute("SS_SN", loginedUser.getSrvno()); 					// 군번
										httpSession.setAttribute("SS_ATHRT", loginedUser.getathrt()); 			// 권한 코드
										httpSession.setAttribute("SS_RNK", loginedUser.getRnkcd_nm()); 				// 계급명
										httpSession.setAttribute("SS_NM", loginedUser.getStmt());					// 이름
										httpSession.setAttribute("SS_IDTF", loginedUser.getIncdt_idtf_cd_nm()); 	// 소속부대
										httpSession.setAttribute("SS_MNT", loginedUser.getmontr());			// 모니터링 권한
										httpSession.setAttribute("SS_YEAR", DateUtil.getCurrentYear()); 			// year
										httpSession.setAttribute("SS_MON", DateUtil.getCurrentMonth()); 			// month
										httpSession.setAttribute("SS_DAY", DateUtil.getCurrentDay()); 				// day						
										
										loginService.InitErrCount(loginedUser);
										loginService.updateLoginDate(loginedUser);
										
										// update login user vo
										loginedUser = loginService.getUserVO(tempUserVO.getSrvno());
										isLogin = true;							

										return "redirect:/index.do";
									}
								}
							}
						}												
					} 
					else {
						System.out.println("pw is false");
						loginService.IncErr(tempUserVO.getSrvno());
						//scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호가 맞지않습니다. " + loginService.CountErr(tempUserVO.getSrvno()) + " / 5');</script>", false);
						scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호가 맞지않습니다.');</script>", false);
						
						if (loginService.CountErr(tempUserVO.getSrvno()) >= 5) {
							System.out.println("pw errCount >= 5");
							scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호를 5회이상 틀려 잠금상태로 변경됩니다. 관리자에게 문의하십시오.'); location.href = 'index.do';</script>", true);
						}
						
						scriptWriter.getInstance().printScript(response, "<script>location.href = 'login.do'</script>", false);
						scriptWriter.getInstance().flush();
						
						return "redirect:/login.do";
					}				
				}					
			} 
			else 
			{
				System.out.println("vo is null");
				scriptWriter.getInstance().printScript(response, "<script>alert('해당 군번은 사용자로 등록되어있지 않습니다.'); location.href = 'login.do';</script>", true);
				return null;
			}
		}
		else {
			scriptWriter.getInstance().printScript(response, "<script>alert('현역 실명인증에 등록되어있지 않습니다. 관리자에게 문의하십시오.'); location.href = 'login.do';</script>", true);
		}
		
		return "redirect:/login.do";
	}
	
	@RequestMapping(value = "logout.do")
	public String logout(@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, HttpSession session) throws Exception {
		if(session.getAttribute("loginedUser") != null) {
			session.invalidate();
			isLogin = false;
			loginedUser = null;
		}			
		return "./main/index";
	}

	// seungwon 19.02.17~ // 시작버튼, 최종완료버튼 눌렀을때
	@RequestMapping(value = "Task_Start.do")
	public String Task_Start(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO)
			throws Exception {
		System.out.println("into task_start.do");

		listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(loginedUser.getSrvno());
		
		// start task
		int succ = taskService.updateTaskStartBySeq(listsearchVO.getSeq()); // by seq	
		System.out.println("success : " + succ);
		
		System.out.println("act ID : " + listsearchVO.getActId());
		
		// 과업을 한명이라도 시작하면 -> 부대활동 시작
		ActivityVO tempActVO = activityService.getActivityById(listsearchVO.getActId());
		
		System.out.println("act VO : " + tempActVO);
		
		if (tempActVO.getState_cd().trim().equals("E1")) {
			System.out.println("in");
			activityService.updateActivityStart(tempActVO);
		}

		// forward
		return "forward:/ReservedSafeManagement_Checklist.do";
	}

	@RequestMapping(value = "Task_Finish.do")
	public String Task_Finish(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO)
			throws Exception {
		System.out.println("into task_finish.do");

		listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(loginedUser.getSrvno());
		
		TaskDataVO taskData = taskDataService.getTaskDataBySeq(listsearchVO.getSeq());

		// 담당자의 과업 종료
		TaskVO task = taskService.getTaskByTaskData(taskData);
		taskService.updateTaskFinishBySeq(task.getSeq());
		
		List<TaskVO> taskList = taskService.getTaskListById(taskData.getId());
		
		int result = 0;
		
		for(int i = 0; i < taskList.size(); ++i) {
			if (taskList.get(i).getState_cd().trim().equals("E3")) 
				++result;
		}
		
		System.out.println("result : "+result);
		
		if (result == taskList.size()) {
			activityService.updateActFinishById(taskData.getId());
		}
		
		return "forward:/ReservedSafeManagement.do";
	}

	// seungwon 19.02.14~ //
	// 부여된 과업에 해당하는 지휘관 지침, 체크리스트 페이지
	@RequestMapping(value = "ReservedSafeManagement_Checklist.do")
	public String ReservedSafeManagement_Checklist(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, @ModelAttribute("activityVO") ActivityVO activityVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
			ModelMap mod) throws Exception {
		System.out.println(listsearchVO.getActId());
		System.out.println(listsearchVO.getSrvno());
		listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd()); // 사용자의 부대코드
		
		TaskDataVO taskData = null;
		TaskDataVO forSearch = null;
		
		if (listsearchVO.getAfter_rqst_data_seq() != null && !(listsearchVO.getAfter_rqst_data_seq().trim().equals(""))) {
			taskData = taskDataService.getTaskDataBySeq(listsearchVO.getAfter_rqst_data_seq());
		}
		else {
			// temp data for search
			forSearch = new TaskDataVO();
			forSearch.setId(listsearchVO.getActId());
			forSearch.setTask_psnchnrg_srvno(loginedUser.getSrvno());
			forSearch.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd());
			System.out.println("forsearch : " + forSearch.getId());
			System.out.println("forsearch : " + forSearch.getTask_psnchnrg_srvno());
			System.out.println("forsearch : " + forSearch.getIncdt_idtf_cd());
			
			taskData = taskDataService.getTaskDataVO(forSearch);
		}
		
		System.out.println("task : " + taskData);		
		
		mod.addAttribute("taskData", taskData);

		return "./main/ReservedSafeManagement_Checklist";
	}

	// seungwon 19.02.15 //
	// 선택한 체크리스트 항목의 디테일
	@RequestMapping(value = "ReservedSafeManagement_Checklist_Detail.do")
	public String ReservedSafeManagement_Checklist_Detail(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			@ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
			ModelMap mod) throws Exception {

		ChecklistItemVO tempItemVO = null;
		System.out.println(listsearchVO.getFindCode());
		System.out.println("getting Item..");
		if (listsearchVO != null)
			tempItemVO = checklistItemService.getChecklistItemVO(listsearchVO);

		System.out.println(tempItemVO.getCtlg_itm_ctnt());

		mod.addAttribute("checklistItem", tempItemVO);		

		return "./main/ReservedSafeManagement_Checklist_Detail";
	}

	@RequestMapping(value = "ListItem_Complete.do")
	public String ListItem_Complete(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("complete Start..");

		ChecklistItemVO tempItemVO = checklistItemService
				.getChecklistItemVO(listsearchVO);

		ActivityVO tempActVO = activityService.getActivityVO(tempItemVO, loginedUser.getIncdt_idtf_cd().trim());

		if (tempActVO.getCtlg_itm_cd_1().trim().equals(tempItemVO.getCtlg_itm_cd().trim())) {
			System.out.println("if =======");
			tempActVO.setCtlg_itm_yn_1("Y ");
			System.out.println("if2 =======");
		} else if (tempActVO.getCtlg_itm_cd_2().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_2("Y ");
		else if (tempActVO.getCtlg_itm_cd_3().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_3("Y ");
		else if (tempActVO.getCtlg_itm_cd_4().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_4("Y ");
		else if (tempActVO.getCtlg_itm_cd_5().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_5("Y ");
		else if (tempActVO.getCtlg_itm_cd_6().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_6("Y ");
		else if (tempActVO.getCtlg_itm_cd_7().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_7("Y ");
		else if (tempActVO.getCtlg_itm_cd_8().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_8("Y ");
		else if (tempActVO.getCtlg_itm_cd_9().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_9("Y ");
		else if (tempActVO.getCtlg_itm_cd_10().equals(
				tempItemVO.getCtlg_itm_cd()))
			tempActVO.setCtlg_itm_yn_10("Y ");

		activityService.updateState(tempActVO);

		System.out.println("complete..");

		listsearchVO.setFindCode(tempActVO.getIncdt_actvt_type_cd());
		System.out.println("into : " + listsearchVO.getFindCode());

		redirectAttributes.addFlashAttribute("listsearchVO", listsearchVO);

		return "redirect:/ReservedSafeManagement_Checklist.do";
	}

	@RequestMapping(value = "TroopsMonitoring_Search.do")
	public String TroopsMonitoring_Search(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpServletRequest request,
			ModelMap mod) throws Exception {
		System.out.println("monitor0..");
		
		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);
		
		if (!(loginedUser.getathrt().trim().equals("B4")))
			listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		
		System.out.println("idtf : " + listsearchVO.getIncdt_idtf_cd());
		if (request.getParameter("date") != null)
			listsearchVO.setDate((String) request.getParameter("date"));
		System.out.println("date : " + listsearchVO.getDate());

		List<ActivityVO> tempActList = activityService.getActivityListByDate(listsearchVO);
		
		if (tempActList != null) {
			String tempStr = "";
			
			for (int i = 0; i < tempActList.size(); ++i) {
				System.out.println(tempActList.get(i).getState_cd().trim());
				
				if (tempActList.get(i).getState_cd().trim().equals("E1")) {	
					tempStr = "-";
					tempActList.get(i).setStart_date(tempStr);
					tempActList.get(i).setFnsh_date(tempStr);					
				}
				else {
					// start time string split
					tempStr = tempActList.get(i).getStart_date().substring(11, 16);
					tempActList.get(i).setStart_date(tempStr);

					if (tempActList.get(i).getState_cd().trim().equals("E3")) {
						
						// finish time string split
						tempStr = tempActList.get(i).getFnsh_date().substring(11, 16);
						tempActList.get(i).setFnsh_date(tempStr);
					} 
					else {
						tempStr = "-";
						tempActList.get(i).setFnsh_date(tempStr);
					}
				}
			}
		}
		
		int pageNo = 1;
		
		if(pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);
		
		Pagination pagination = new Pagination();
		pagination.setCountPerPage(8);
		pagination.setPaginationInfo(tempActList, pageNo);
		
		mod.addAttribute("activityList", pagination.getListPerPage(tempActList));
		mod.addAttribute("paginationInfo", pagination.getPaginationInfo());
		System.out.println("monitor3..");

		return "forward:/TroopsMonitoring.do";
	}

	@RequestMapping(value = "TroopsMonitoring_Activity_Detail.do")
	public String TroopsMonitoring_TaskSearch(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			ModelMap mod) throws Exception {

		if(listsearchVO.getIncdt_idtf_cd() == null || listsearchVO.getIncdt_idtf_cd() == "") {
			listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		}
		List<TaskVO> tempTaskList = taskService.getTaskListById(listsearchVO.getActId());
		System.out.println("list : " + tempTaskList);
		ActivityVO tempActVO = activityService.getActivityById(listsearchVO.getActId());
		
		System.out.println("act : " + tempActVO);

		if(tempTaskList != null)
		{
			String tempStr = "";
			for (int i = 0; i < tempTaskList.size(); ++i) {
				if (tempTaskList.get(i).getState_cd().equals("E1")) {
					tempStr = "-";
					tempTaskList.get(i).setStart_date(tempStr);
					tempTaskList.get(i).setFnsh_date(tempStr);					
				} 
				else {
					// start time string split
					if(tempTaskList.get(i).getStart_date() != null) {
						tempStr = tempTaskList.get(i).getStart_date().substring(11, 16);
						tempTaskList.get(i).setStart_date(tempStr);
					}
					else {
						System.out.println("getStart_date() is null");
						tempStr = "-";
						tempTaskList.get(i).setStart_date(tempStr);
					}
					

					if (tempTaskList.get(i).getState_cd().trim().equals("E3")) {
						// finish time string split
						if(tempTaskList.get(i).getFnsh_date() != null) {
							tempStr = tempTaskList.get(i).getFnsh_date().substring(11, 16);
							tempTaskList.get(i).setFnsh_date(tempStr);
						}	
						else {
							System.out.println("getFnsh_date() is null");
							tempStr = "-";
							tempTaskList.get(i).setFnsh_date(tempStr);
						}
					} else {
						tempStr = "-";
						tempTaskList.get(i).setFnsh_date(tempStr);
					}
				}
			}
		}
		
		mod.addAttribute("actTaskList", tempTaskList);
		mod.addAttribute("actVO", tempActVO);

		return "./main/TroopsMonitoring_Activity_Detail";
	}

	// 수시 안전간리 활동 요청
	@RequestMapping(value = "OccasionalSafeManagement_Request.do")
	public String OccasionalSafeManagement_Request(
			@ModelAttribute("taskVO") TaskVO taskVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
			HttpServletRequest request,			   
			ModelMap model,
			   HttpSession httpSession
				) throws Exception
				{
				if (httpSession.getAttribute("loginedUser") == null) {
				return "redirect:/login.do";
				}

		List<TaskVO> taskLarge = taskService.getTaskLarge(membersearchVO);
		model.addAttribute("taskLarge", taskLarge);

		List<TaskVO> taskMiddle = taskService.getTaskMiddle(membersearchVO);
		model.addAttribute("taskMiddle", taskMiddle);

		List<TaskVO> taskList = taskService.getTaskList(membersearchVO);
		model.addAttribute("taskList", taskList);

		List<CheckCodeVO> checkCodeList = checkCodeService
				.getCheckList(membersearchVO);
		model.addAttribute("checkCodeList", checkCodeList);

		model.addAttribute("membersearchVO", membersearchVO);

		return "./main/OccasionalSafeManagement_Request";
	}

	// add safe request
	@RequestMapping(value = "Add_SafeRequest.do")
	public String Add_SafeRequest(
			@ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO)
			throws Exception {
		// add safe request to database

		// 요청자 군번, 기본 상태 코드 ( 승인대기 )
		requestActivityVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		requestActivityVO.setRqstr_srvno(loginedUser.getSrvno());
		requestActivityVO.setState_cd("D1");

		// 예외처리
		requestActivityService.insertRequestActivityVO(requestActivityVO);

		System.out.println("do redirect");
		return "redirect:/OccasionalSafeManagement.do";
	}
	
	@RequestMapping(value = "ReservedSafeManagement_Commander_Detail.do")
	public String ReservedSafeManagement_Commander_Detail(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(loginedUser.getSrvno());
		
		ActivityVO actVO = activityService.getActivityVO(listsearchVO);
		System.out.println(actVO.getguidnc_yn_2());
		
		model.addAttribute("cmdNum", listsearchVO.getCmdNum());
		model.addAttribute("activity", actVO);
		
		return "./main/ReservedSafeManagement_Commander_Detail";
	}

	// 긴급승인요청
	@RequestMapping(value = "RequestApproval.do")
	public String RequestApproval(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			@ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
			@ModelAttribute("checkApprovalVO") CheckApprovalVO checkApprovalVO,
			ModelMap mod) throws Exception {
		System.out.println(checklistItemVO.getCtlg_cd());
		System.out.println(checklistItemVO.getCtlg_itm_cd());
		System.out.println(checklistItemVO.getCtlg_itm_ctnt());
		
		System.out.println("ID : " + listsearchVO.getActId());
		System.out.println("TASK SRVNO : " + listsearchVO.getSrvno());
		System.out.println("DATA SEQ : " + listsearchVO.getSeq());
		
		ChecklistItemVO tempItemVO = null;
		System.out.println(listsearchVO.getFindCode());
		System.out.println("getting Item..");
		if (listsearchVO != null)
			tempItemVO = checklistItemService.getChecklistItemVO(listsearchVO);

		System.out.println(tempItemVO.getCtlg_itm_ctnt());
		
		TaskDataVO taskData = taskDataService.getTaskDataBySeq(listsearchVO.getSeq().trim());
		System.out.println("data : " + taskData);
		// check
		String state = "";
		if(taskData != null) {
			if (taskData.getCtlg_itm_cd_1().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_1();
			else if (taskData.getCtlg_itm_cd_2().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_2();
			else if (taskData.getCtlg_itm_cd_3().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_3();
			else if (taskData.getCtlg_itm_cd_4().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_4();
			else if (taskData.getCtlg_itm_cd_5().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_5();
			else if (taskData.getCtlg_itm_cd_6().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_6();
			else if (taskData.getCtlg_itm_cd_6().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_6();
			else if (taskData.getCtlg_itm_cd_7().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_7();
			else if (taskData.getCtlg_itm_cd_8().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_8();
			else if (taskData.getCtlg_itm_cd_9().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_9();
			else if (taskData.getCtlg_itm_cd_10().trim().equals(tempItemVO.getCtlg_itm_cd().trim()))
				state = taskData.getCtlg_itm_yn_10();
		}	

		mod.addAttribute("checklistItem", tempItemVO);
		
		mod.addAttribute("listsearchVO", listsearchVO);
		mod.addAttribute("checkState", state);
		
		//mod.addAttribute("checklistItem", checklistItemVO);
		
		return "./main/RequestApproval";
	}

	// add request approval
	@SuppressWarnings("static-access")
	@RequestMapping(value = "Add_RequestApproval.do")
	public String Add_RequestApproval(
			@ModelAttribute("checkApprovalVO") CheckApprovalVO checkApprovalVO,
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			RedirectAttributes redirectAttributes, HttpServletResponse response)
			throws Exception {
		
		CheckApprovalVO tempAppVO = checkApprovalService.getApprovalByVO(checkApprovalVO);
		
		if(tempAppVO != null) {
			scriptWriter.getInstance().printScript(response, "<script>alert('이미 요청되어있습니다.'); location.href = history.back();</script>", true);
		}
		else {
			System.out.println("start");
			System.out.println("task seq : " + checkApprovalVO.getTaskDataSeq());
			TaskDataVO taskData = taskDataService.getTaskDataBySeq(checkApprovalVO.getTaskDataSeq());
			System.out.println("task data vo : " + taskData);
			System.out.println(taskData.getState_cd());
			
			System.out.println("rsn : " + checkApprovalVO.getRsn());

			// insert approval into database
			checkApprovalVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim()); // incdt_idtf_cd
			checkApprovalVO.setRqstr_srvno(loginedUser.getSrvno()); // srvno
			
			// get act code
			ActivityTypeVO actType = activityTypeService.getActTypeByCtlg(taskData.getCtlg_cd());
			System.out.println("act type vo : " + actType);
			
			checkApprovalVO.setIncdt_actvt_type_cd(actType.getIncdt_actvt_type_cd()); // incdt_actvt_cd
			checkApprovalVO.setActvt_date(taskData.getActvt_date());	// act date
			checkApprovalVO.setState_cd("D1");	// state_cd = default : D1, D2, D3
			
			System.out.println(checkApprovalVO.getActvt_date());
			
			checkApprovalService.insertApproval(checkApprovalVO);
			
			System.out.println("insert complete");
			System.out.println("task itm 1 : " + taskData.getCtlg_itm_cd_1().trim());
			System.out.println("chk itm 1 : " + checkApprovalVO.getCtlg_itm_cd().trim());
			
			// change item state code in activity AND update
			if (taskData.getCtlg_itm_cd_1() != null	&& !(taskData.getCtlg_itm_cd_1().equals(""))) {
				if(taskData.getCtlg_itm_cd_1().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_1("C ");
			}
			if (taskData.getCtlg_itm_cd_2() != null	&& !(taskData.getCtlg_itm_cd_2().equals(""))) {
				if(taskData.getCtlg_itm_cd_2().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_2("C ");
			}
			if (taskData.getCtlg_itm_cd_3() != null	&& !(taskData.getCtlg_itm_cd_3().equals(""))) {
				if(taskData.getCtlg_itm_cd_3().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_3("C ");
			}
			if (taskData.getCtlg_itm_cd_4() != null	&& !(taskData.getCtlg_itm_cd_4().equals(""))) {
				if(taskData.getCtlg_itm_cd_4().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_4("C ");
			}
			if (taskData.getCtlg_itm_cd_5() != null	&& !(taskData.getCtlg_itm_cd_5().equals(""))) {
				if(taskData.getCtlg_itm_cd_5().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_5("C ");
			}
			if (taskData.getCtlg_itm_cd_6() != null	&& !(taskData.getCtlg_itm_cd_6().equals(""))) {
				if(taskData.getCtlg_itm_cd_6().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_6("C ");
			}
			if (taskData.getCtlg_itm_cd_7() != null	&& !(taskData.getCtlg_itm_cd_7().equals(""))) {
				if(taskData.getCtlg_itm_cd_7().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_7("C ");
			}
			if (taskData.getCtlg_itm_cd_8() != null	&& !(taskData.getCtlg_itm_cd_8().equals(""))) {
				if(taskData.getCtlg_itm_cd_8().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_8("C ");
			}
			if (taskData.getCtlg_itm_cd_9() != null	&& !(taskData.getCtlg_itm_cd_9().equals(""))) {
				if(taskData.getCtlg_itm_cd_9().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_9("C ");
			}
			if (taskData.getCtlg_itm_cd_10() != null	&& !(taskData.getCtlg_itm_cd_10().equals(""))) {
				if(taskData.getCtlg_itm_cd_10().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
					taskData.setCtlg_itm_yn_10("C ");
			}			
			
			System.out.println("chk itm 1 : " + checkApprovalVO.getCtlg_itm_cd().trim());
			System.out.println("task itm 1 : " + taskData.getCtlg_itm_cd_10());
			
			//activityService.updateState(tempActVO);
			taskDataService.updateYNState(taskData);
			
			System.out.println("finish");
			String seqString = (taskData.getSeq()).toString();
			listsearchVO.setAfter_rqst_data_seq(seqString);
			redirectAttributes.addFlashAttribute("listsearchVO", listsearchVO);
		}		
		
		return "redirect:/ReservedSafeManagement_Checklist.do";
	}
	
	// 체크리스트 추가/수정 요청 페이지
	@RequestMapping(value = "CheckListManagement_Request.do")
	public String CheckListManagement_Request(
			@ModelAttribute("taskVO") TaskVO taskVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
			@ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO,
			HttpServletRequest request, ModelMap model) throws Exception {		
		// 부대활동 리스트
		List<ChecklistVO> tempList = checklistService.getAllChecklist(loginedUser);		
		model.addAttribute("checkList", tempList);
		
		List<TaskVO> taskLarge = taskService.getTaskLarge(membersearchVO);
		model.addAttribute("taskLarge", taskLarge);

		List<TaskVO> taskMiddle = taskService.getTaskMiddle(membersearchVO);
		model.addAttribute("taskMiddle", taskMiddle);

		List<TaskVO> taskList = taskService.getTaskList(membersearchVO);
		model.addAttribute("taskList", taskList);

		List<CheckCodeVO> checkCodeList = checkCodeService
				.getCheckList(membersearchVO);
		model.addAttribute("checkCodeList", checkCodeList);

		model.addAttribute("membersearchVO", membersearchVO);
		
		return "./main/CheckListManagement_Request";
	}
	
	// 체크리스트 추가/수정 요청 처리 함수
	@RequestMapping(value = "Add_RequestChecklist.do")
	public String Add_RequestChecklist(
			@ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO) throws Exception {
		// 요청 체크리스트에 요청자 군번, 상태코드 부여
		requestChecklistVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		requestChecklistVO.setRqstr_srvno(loginedUser.getSrvno());
		requestChecklistVO.setState_cd("D1");
		
		requestChecklistService.insertRequestChecklist(requestChecklistVO);
		
		return "redirect:/CheckListManagement.do";
	}
	
	// 지휘관지침 완료
	@RequestMapping(value = "CmdList_Complete.do")
	public String CmdList_Complete(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			RedirectAttributes redirectAttributes) throws Exception {
		
		listsearchVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(loginedUser.getSrvno());
		
		ActivityVO actVO = activityService.getActivityVO(listsearchVO);
		if(listsearchVO.getCmdNum().equals("1"))
			actVO.setguidnc_yn_1("Y ");
		if(listsearchVO.getCmdNum().equals("2"))
			actVO.setguidnc_yn_2("Y ");
		if(listsearchVO.getCmdNum().equals("3"))
			actVO.setguidnc_yn_3("Y ");
		
		activityService.updateState(actVO);
		
		listsearchVO.setFindCode(actVO.getIncdt_actvt_type_cd());
		System.out.println("into : " + listsearchVO.getFindCode());

		redirectAttributes.addFlashAttribute("listsearchVO", listsearchVO);
		
		return "redirect:/ReservedSafeManagement_Checklist.do";
	}
	
	@RequestMapping(value = "Authorization.do")
	public String Authorization(
			HttpServletRequest request,
			ModelMap model) throws Exception {
		HttpSession httpSession = request.getSession();
		String returnUrl = "";
		
		String identity_page = "http://army.mil.kr/vfct/vfctP.do"; // 추후 제공되는 인증페이지 url로 바꿔야함
		String this_page = request.getRequestURL().toString();
		String auth_page = request.getParameter("authURL");
		String sec = request.getParameter("enkey");
		String ss_key = "";
		ss_key = (String)httpSession.getAttribute("SS_KEY");
		
		double k;
		String key = "";
		
		if(ss_key == null || ss_key == "") {
			httpSession.setAttribute("SS_KEY", key);
		}
		
		ss_key = (String)httpSession.getAttribute("SS_KEY");
		
		if(httpSession.getAttribute("SS_NAME") == null) {
			// 실명인증값이 없는 경우
			if(this_page.equals(auth_page)) {
				// 실명인증 모듈에 갔다 왔을 경우
				String sn = request.getParameter("sn");
				String nm = URLDecoder.decode(request.getParameter("nm"), "UTF-8");
				String rk = URLDecoder.decode(request.getParameter("rk"), "UTF-8");
				String pn = URLDecoder.decode(request.getParameter("pn"), "UTF-8");
				String un = URLDecoder.decode(request.getParameter("un"), "UTF-8");
				String rkcd = request.getParameter("rkcd");
				String email = request.getParameter("email");
				
				httpSession.setAttribute("SS_SN", sn.trim());
				httpSession.setAttribute("SS_RANK", rk.trim());
				httpSession.setAttribute("SS_RKCD", rkcd.trim());
				httpSession.setAttribute("SS_NAME", nm.trim());
				httpSession.setAttribute("SS_UN", un.trim());
				httpSession.setAttribute("SS_PN", pn.trim());
				httpSession.setAttribute("SS_EMAIL", email.trim());
				
				return "redirect:/login.do"; // 돌아갈 페이지
			}
			else {
				// 실명인증 모듈을 처음 호출하는 경우
				model.addAttribute("identity_page", identity_page);
				model.addAttribute("SS_KEY", key);
				model.addAttribute("originalurl", this_page);
				model.addAttribute("sn", "");
				
				return "./main/Authorization";
			}
		}
		else {
			// 실명인증 값이 있는 경우
			return "redirect:/index.do";
		}
	}
	
	@RequestMapping(value = "PasswordChange.do")
	public String PasswordChange() throws Exception {		
		return "./main/PasswordChange";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "PasswordChangeAction.do")
	public String PasswordChangeAction(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws Exception {
		String srvno = "";
		String pw_curr = request.getParameter("pw_current");
		String pw_new = request.getParameter("pw_new");
		String pw_new_conf = request.getParameter("pw_new_confirm");
		
		System.out.println("pw_curr : " + pw_curr);
		System.out.println("pw_new : " + pw_new);
		System.out.println("pw_new_conf : " + pw_new_conf);
		
		if(httpSession.getAttribute("changePwSrv") != null) {
			System.out.println((String)httpSession.getAttribute("changePwSrv"));
			srvno = (String) httpSession.getAttribute("changePwSrv");
		}
		else {
			scriptWriter.getInstance().printScript(response, "<script>alert('사용자 정보를 찾지 못했습니다. 관리자에게 문의하십시오.'); location.href = 'index.do';</script>", true);
			return null;
		}		
		
		UserVO userVO = loginService.getUserVO(srvno);
		if(userVO != null) {
			if(userVO.getPw().equals(pw_curr)) {
				if(pw_new.equals(pw_new_conf)) {
					userVO.setPw(pw_new);
					if(userVO.getacc_state_info().equals("K2"))	{
						userVO.setacc_state_info("K1");
						loginService.updateUserStatus(userVO);
					}
						
					loginService.updatePassword(userVO);
					
					return "redirect:/login.do";
				}
				else {
					scriptWriter.getInstance().printScript(response, "<script>alert('변경할 비밀번호와 확인 비밀번호가 일치하지 않습니다. 다시 시도해주십시오'); location.href = 'PasswordChange.do';</script>", true);
				}
			}
			else {
				scriptWriter.getInstance().printScript(response, "<script>alert('현재 비밀번호가 일치하지 않습니다. 다시 시도해주십시오'); location.href = 'PasswordChange.do';</script>", true);
			}
		}
		else {
			scriptWriter.getInstance().printScript(response, "<script>alert('사용자 정보가 존재하지 않습니다. 관리자에게 문의하십시오'); location.href = 'index.do';</script>", true);
		}
		
		return "redirect:/login.do";
	}
	
	@RequestMapping(value="GroupNameSearch.do")											// 과업 분류하는 기능
	public String GroupNameSearch(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		String name = request.getParameter("Name");
		String code =  request.getParameter("Code");
		String Large = request.getParameter("Large");
		String Middle = request.getParameter("Middle");
		
			//로그인 기능 미구현으로 인한 대체
			
		taskVO.setCtlg_nm(name);
		taskVO.setCtlg_cd(code);
		taskService.getTaskSearch(taskVO);
		membersearchVO.setSearchConditionLarge(Large);
		membersearchVO.setSearchConditionMiddle(Middle);
		taskVO.setLrgcls(Large);
		taskVO.setMdcls(Middle);
		
		List<TaskVO> taskLarge = taskService.getTaskLarge(membersearchVO);
		model.addAttribute("taskLarge", taskLarge);
		
		List<TaskVO> taskMiddle = taskService.getTaskMiddle(membersearchVO);
		
		
		
		 return "/main/Modal";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="GroupNameSearchLarge.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String GroupNameSearchLarge(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model
			) throws Exception {
		
				String Large = request.getParameter("Large");
			
				String jsonString = request.getParameter("jsonString");		 //request를 사용하여 jsonString 저장
				   
				jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
				   
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
					   
						   
				membersearchVO.setSearchConditionLarge(Large);
				List<TaskVO> taskLarge = taskService.getTaskLarge(membersearchVO);
				List<TaskVO> taskMiddle = taskService.getTaskMiddle(membersearchVO);
				
				int largelenth = taskLarge.size();
				int length = taskMiddle.size();
					String newResult = "";
					JSONArray jaLarge = new JSONArray();
					JSONArray jaMiddle = new JSONArray();

					for(int i = 0; i<largelenth; i++){
						JSONObject joLarge = new JSONObject();
						String Large_cd 	= taskLarge.get(i).cd;
						String Large_cd_nm 	= taskLarge.get(i).cd_nm;
					   	
						joLarge.put("Large_cd"		, Large_cd);
						joLarge.put("Large_cd_nm"	,Large_cd_nm);
					   
						jaLarge.add(joLarge);
				   		}
	
					for(int i = 0; i<length; i++){
						JSONObject joMiddle = new JSONObject();
					   	String Middle_cd 	= taskMiddle.get(i).cd;
					   	String Middle_cd_nm = taskMiddle.get(i).cd_nm;
	
					   	joMiddle.put("Middle_cd"	, Middle_cd);
					   	joMiddle.put("Middle_cd_nm"	,Middle_cd_nm);
					   	
					   	jaMiddle.add(joMiddle);
				   		}
				JSONObject resultjson = new JSONObject();
				resultjson.put("Middle"	, jaMiddle);
				
				
				String jsonResult = jaMiddle.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
				
				return jsonResult; //ajax의 result로 리턴됨
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="GroupNameSearchMiddle.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String GroupNameSearchMiddle(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model
			) throws Exception {
		
				String Middle = request.getParameter("Middle");
			
				String jsonString = request.getParameter("jsonString");		 //request를 사용하여 jsonString 저장
				   
				jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
				
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
					   
						   
				taskVO.setMdcls(Middle);
				List<TaskVO> TaskSearchList = taskService.getTaskSearch(taskVO);
				
				int length = TaskSearchList.size();
				JSONArray jaTask = new JSONArray();
				
				for(int i=0;i<length;i++){
					JSONObject joTask = new JSONObject();
					joTask.put("ctlg_cd", TaskSearchList.get(i).ctlg_cd);
					joTask.put("ctlg_nm", TaskSearchList.get(i).ctlg_nm);
					joTask.put("lrgcls", TaskSearchList.get(i).lrgcls);
					joTask.put("mdcls", TaskSearchList.get(i).mdcls);
					
					jaTask.add(joTask);

				}
				
				String jsonResult = jaTask.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경

				return jsonResult; //ajax의 result로 리턴됨
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="GroupNameSearchName.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String GroupNameSearchName(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model
			) throws Exception {
		
		String TaskNameSearch = request.getParameter("TaskNameSearch");
		String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
			   
		taskVO.setCtlg_nm(TaskNameSearch);
		List<TaskVO> TaskSearchList = taskService.getTaskSearchName(taskVO);
		
		int length = TaskSearchList.size();
		
		JSONArray jaTask = new JSONArray();
		
		for(int i=0;i<length;i++){
			JSONObject joTask = new JSONObject();
			joTask.put("ctlg_cd", TaskSearchList.get(i).ctlg_cd);
			joTask.put("ctlg_nm", TaskSearchList.get(i).ctlg_nm);
			joTask.put("lrgcls", TaskSearchList.get(i).lrgcls);
			joTask.put("lrgcls_nm", TaskSearchList.get(i).lrgcls_nm);
			joTask.put("mdcls", TaskSearchList.get(i).mdcls);
			joTask.put("mdcls_nm", TaskSearchList.get(i).mdcls_nm);
			
			jaTask.add(joTask);
		}
		
		String jsonResult = jaTask.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println(jsonResult);

		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="GroupNameSearchCode.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String GroupNameSearchCode(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model
			) throws Exception {
		
		String TaskNameSearch = request.getParameter("TaskNameSearch");
		String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
			   
		taskVO.setCtlg_cd(TaskNameSearch);
		List<TaskVO> TaskSearchList = taskService.getTaskSearchName(taskVO);
		
		int length = TaskSearchList.size();
		
		JSONArray jaTask = new JSONArray();
		
		for(int i=0;i<length;i++){
			JSONObject joTask = new JSONObject();
			joTask.put("ctlg_cd", TaskSearchList.get(i).ctlg_cd);
			joTask.put("ctlg_nm", TaskSearchList.get(i).ctlg_nm);
			joTask.put("lrgcls", TaskSearchList.get(i).lrgcls);
			joTask.put("lrgcls_nm", TaskSearchList.get(i).lrgcls_nm);
			joTask.put("mdcls", TaskSearchList.get(i).mdcls);
			joTask.put("mdcls_nm", TaskSearchList.get(i).mdcls_nm);
			
			jaTask.add(joTask);
		}
		
		String jsonResult = jaTask.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println(jsonResult);

		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@RequestMapping(value="GroupNameSearchResult.do")
	public void GroupNameSearch_result(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		String name = request.getParameter("Name");
		
		taskVO.setCtlg_nm(name);
		taskService.getTaskSearch(taskVO);
		
		List<TaskVO> TaskSearchList = taskService.getTaskSearch(taskVO);
		
		List<TaskVO> taskLarge = taskService.getTaskLarge(membersearchVO);
		model.addAttribute("taskLarge", taskLarge);
		
		List<TaskVO> taskMiddle = taskService.getTaskMiddle(membersearchVO);
		model.addAttribute("taskMiddle", taskMiddle);
		
		String SearchResult =TaskSearchList.get(0).getCtlg_nm().toString();
		
		response.getWriter().print(SearchResult);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="ActivityAjax.do", produces="text/html;charset=utf-8")
	@ResponseBody
	public String ActivityAjax(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model)
	throws Exception{
		String jsonString = request.getParameter("jsonString");
			System.out.println("Controller > jsonString : "+ jsonString);
			jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			System.out.println("Controller > jsonString.replace() : "+jsonString);
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString); //jsonString을 json객체로 변경
		
		String Name = (String)json.get("Name");					//jsonString 에서 받은 체크리스트 이름 확인
		System.out.println(Name);
		
		taskVO.setCtlg_nm(Name);								//taskVO에 jsonString 입력
		
		List<TaskVO> TaskSearchList = taskService.getTaskSearch(taskVO);
		 int length = TaskSearchList.size();
		   for(int i = 0; i<length; i++){
			   	String ctlg_cd = TaskSearchList.get(i).ctlg_cd;
			   	String ctlg_nm = TaskSearchList.get(i).ctlg_nm;
			   	
			   	json.put("cd", ctlg_cd);
			   	json.put("nm",ctlg_nm);
		   		}	   	

		String jsonResult = json.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		
		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@RequestMapping(value="UserSearchModal.do")
	public String UserSearchModal(
			@ModelAttribute("memberVO") MemberVO memberVO,
			@ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
			ModelMap model, 
			String flag, 
			HttpSession httpSession,
			HttpServletRequest request
			)throws Exception
			{
				if (httpSession.getAttribute("loginedUser") == null) {
				return "redirect:/login.do";
				}
		membersearchVO.setSearchCode(loginedUser.getIncdt_idtf_cd().trim());
		membersearchVO.setSearchKeyword2("1");
		
		List<MemberVO> memberList = memberService.getMemberList(membersearchVO);
			
		if(membersearchVO.getSearchCondition() != null)
		{
			
		}
		else
		{
			membersearchVO.setSearchCondition("00");
		}
		model.addAttribute("memberList", memberList);
		model.addAttribute("membersearchVO", membersearchVO);
		return  "/main/AssignTask_Modal";
	}
	
	@RequestMapping(value="TaskSmallSearchModal.do")
	public String TaskSmallSearchModal
	()throws Exception{
		return "/main/TaskSmallSearchModal";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="TaskSmallSearch", produces="text/html; charset=utf-8")
	@ResponseBody
	public String TaskSmallSearch(
			SptlyCodeVO sptlyCodeVO,
			TaskDataVO taskDataVO,
			MemberSearchVO memberSearchVO,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model)
	throws Exception
	{
		String jsonString = request.getParameter("jsonString");
		jsonString = jsonString.replaceAll("&quot;", "\"");
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject)parser.parse(jsonString);
		
		String code = (String) json.get("Name");
		
		taskDataVO.setTask_cd(code);
		taskDataVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
		sptlyCodeVO.setCtlg_cd(code);
		sptlyCodeVO.setIncdt_idtf_cd(loginedUser.getIncdt_idtf_cd().trim());
				
//		List<TaskDataVO> PresetList = taskDataService.getTaskDataList(taskDataVO);
		List<SptlyCodeVO> PresetList = sptlyCodeService.getTaskDataList(sptlyCodeVO);
		int length = PresetList.size();
		JSONArray jaList = new JSONArray();
		for(int i=0;i<length;i++){
			JSONObject joTask = new JSONObject();
			joTask.put("task_nm",PresetList.get(i).getTask());
			joTask.put("seq",PresetList.get(i).getSeq());
			jaList.add(joTask);
		}
		
		String jsonResult = jaList.toString();
		
		return jsonResult;
	}
	@RequestMapping(value="TroopsSearchModal.do")
	public String TroopsSearchModal(
			@ModelAttribute("troopsVO") TroopsVO troopsVO,
			@ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
			ModelMap model, String flag, HttpServletRequest request
			)throws Exception{

		List<TroopsVO> TroopsList = troopsService.getTroopsList(troopsVO);
		model.addAttribute("TroopsList", TroopsList);
		return  "/main/Troops_Modal";
	}
	
	@RequestMapping(value = "KeyCheck.do")
	public String KeyCheck(HttpServletRequest request, HttpSession httpSession) throws Exception {
		httpSession.setAttribute("SS_SN", request.getParameter("srvno"));
		httpSession.setAttribute("SS_PW", request.getParameter("pw"));
		return "./main/KeyCheck";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "KeyCheck_Action.do")
	public String KeyCheck_Action(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, ModelMap model) throws Exception {
		String result = request.getParameter("safeck_rslt");
		String srvno = request.getParameter("safeck_sn");
		System.out.println("result : " + result);
		System.out.println("srvno : " + srvno);
		String flushStr = "";
		String returnUrl = "";
		boolean flag = false;
		
		flushStr += "<script>";		
		
		if (result == null || !result.equals("0")) {
			if (result.equals("1")) {
				flushStr += "alert('검증체계에 등록되지 않은 군번입니다. 관리자에게 문의하십시오');";
				returnUrl = "redirect:/index.do";
			}				
			else if (result.equals("2")) {
				flushStr += "alert('당일 해당군번에 대한 인증 5회 실패로 오늘은 검증 불가능합니다. \n\n 내일 다시 시도하시길 바랍니다.');";
				returnUrl = "redirect:/index.do";
			}				
			else if (result.equals("3")) {
				flushStr += "alert('현역인증실명 키가 일치하지 않습니다. \n\n인트라넷 '육군포털>모바일육군'에서 확인 후 입력바랍니다.'); history.back();";
				returnUrl = "";
			}				
		}		
		else {
			httpSession.setAttribute("SS_RSLT", flag);
			returnUrl = "redirect:/loginAction.do";
		}
		
		flushStr += "</script>";
		
		System.out.println(flushStr);
		
		if(!result.equals("0"))
			scriptWriter.getInstance().printScript(response, flushStr, true);
		
		return returnUrl;
	}
	
    @SuppressWarnings("static-access")
	@RequestMapping(value = "excelUploadAjax.do", method = RequestMethod.POST)
    public String excelUploadAjax(MultipartHttpServletRequest request, HttpServletResponse response)  throws Exception{
		System.out.println("excel Start");
        MultipartFile excelFile = request.getFile("excelFile");
        
        //HandlerFile handlerFile = new HandlerFile(request);
        //String filePath = handlerFile.getFilePath();
        
        System.out.println("filePath : " + excelFile);
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        
        File destFile = new File("C:\\"+excelFile.getOriginalFilename());
        excelFile.transferTo(destFile);        
        
        List<CodeVO> tempList = new ArrayList<CodeVO>();
        
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D");
        excelReadOption.setStartRow(1);        
        
        List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);        
        
        for(Map<String, String> article: excelContent){
            System.out.println(article.get("A"));
            System.out.println(article.get("B"));
            System.out.println(article.get("C"));
            System.out.println(article.get("D"));
            
            if((article.get("A") == null || article.get("A").equals(""))
            		|| (article.get("B") == null || article.get("B").equals(""))
            		|| (article.get("C") == null || article.get("C").equals(""))
            		|| (article.get("D") == null || article.get("D").equals(""))) {
            	scriptWriter.getInstance().printScript(response, "<script>alert('파일 내에 누락된 정보가 있습니다. 파일을 확인해주십시오.'); location.href = 'Code_Insert_Excel.do';</script>", true);
            }
            
            CodeVO codeVO = new CodeVO();
            
            codeVO.setCd(article.get("A"));
            codeVO.setCd_nm(article.get("B"));
            codeVO.setRmrk(article.get("C"));
            codeVO.setGrp_cd(article.get("D"));
            
            tempList.add(codeVO);
        }
        
        codeService.insertCodeListInExcel(tempList);
        
        return "redirect:/ManageCode.do";
    }
    
	// 코드관리
	@RequestMapping(value = "ManageCode.do")
	public String ManageCode(
			@ModelAttribute("codeVO") CodeVO codeVO,
			@ModelAttribute("codeSearchVO") CodeSearchVO codeSearchVO,
			ModelMap model, String flag, HttpServletRequest request) throws Exception {		
		
		System.out.println("search nm : " + codeSearchVO.getSearch_nm());
		
		/** paging setting */
		//PaginationInfo paginationInfo = new PaginationInfo();		
		
		String pageNoStr = request.getParameter("currentPageNo");
		String currentSelected = request.getParameter("currentSelected");
		String currentSearch = request.getParameter("currentSearch");
		System.out.println("selected : " + currentSelected);
		if(codeSearchVO.getSearch_dvs() == null || codeSearchVO.getSearch_dvs().equals(""))
			codeSearchVO.setSearch_dvs(currentSelected);
		if(codeSearchVO.getSearch_nm() == null || codeSearchVO.getSearch_nm().equals(""))
			codeSearchVO.setSearch_nm(currentSearch);
		
		int pageNo = 1;
		
		if(pageNoStr != null && !pageNoStr.equals(""))
			pageNo = Integer.parseInt(pageNoStr);

		System.out.println("pageNo : " + pageNo);
		
		List<CodeVO> codeList = null;
		
		// 총 객체 갯수를 page 클래스에 입력
		if((codeSearchVO.getSearch_dvs() == null || codeSearchVO.getSearch_dvs().equals(""))
				&& (codeSearchVO.getSearch_nm() == null || codeSearchVO.getSearch_nm().equals(""))) {
			codeList = codeService.getAllCodeList();
		}
		else {		
			System.out.println("dvs or nm not null");
			codeList = codeService.getCodeList(codeSearchVO);
		}
		/*
		int listCnt = codeList.size(); // 총 목록 갯수
		int pageSize = 10; // 한 화면에 보여줄 페이지 갯수
		
		paginationInfo.setTotalRecordCount(listCnt);		// 총 게시물 갯수
		paginationInfo.setCurrentPageNo(pageNo);			// 최근 페이지 넘버
		paginationInfo.setRecordCountPerPage(codeSearchVO.getRecordCountPerPage());	// 한페이지당 게시물 갯수
		paginationInfo.setPageSize(pageSize);				// 한화면 하단에 페이치 갯수

		int start = (pageNo - 1) * 10;
		int end = start + paginationInfo.getRecordCountPerPage();
		if(end > listCnt)
			end = listCnt;
			
		System.out.println("codeList size" + codeList.size());
		
		List<CodeVO> viewList = codeList.subList(start, end);
		*/
		
		Pagination pagination = new Pagination();
		pagination.setPaginationInfo(codeList, pageNo);
		
		// get group code list
		List<GroupCodeVO> viewGroupList = groupCodeService.getAllGroupCodeList();

		model.addAttribute("groupList", viewGroupList);
		model.addAttribute("codeList", pagination.getListPerPage(codeList));
		model.addAttribute("paginationInfo", pagination.getPaginationInfo());
		
		return "./main/ManageCode";
	}
	
	// 코드 한개 입력
	@RequestMapping(value = "Code_Insert.do")
	public String Code_Insert(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		// get group code list
		List<GroupCodeVO> viewGroupList = groupCodeService.getAllGroupCodeList();

		model.addAttribute("groupList", viewGroupList);
		return "./main/Code_Insert";
	}
	
	// input excel
	@RequestMapping(value = "Code_Insert_Excel.do")
	public String Code_Inser_Excel() throws Exception {
		return "./main/Code_Insert_Excel";
	}
	
	// 코드 수정
	@RequestMapping(value = "Code_Modify.do")
	public String Code_Modify(
			@ModelAttribute("codeVO") CodeVO codeVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		String cd = request.getParameter("cd");
		
		CodeVO tempCodeVO = codeService.getCodeVO(cd);
		
		model.addAttribute("modCode", tempCodeVO);
		
		return "./main/Code_Modify";
	}
	
	@RequestMapping(value = "Code_Insert_Action.do")
	public String Code_Insert_Action(@ModelAttribute("codeVO") CodeVO codeVO) throws Exception {
		// insert
		codeService.insertCodeVO(codeVO);
		
		return "redirect:/ManageCode.do";
	}
	
	@RequestMapping(value = "Code_Modify_Action.do")
	public String Code_Modify_Action(@ModelAttribute("codeVO") CodeVO codeVO) throws Exception {
		// update
		codeService.updateCodeVO(codeVO);
		
		return "redirect:/ManageCode.do";
	}
	
	// 코드 삭제
	@RequestMapping(value = "Code_Delete_Action.do")
	public String Code_Delete(@ModelAttribute("codeVO") CodeVO codeVO, HttpServletRequest request) throws Exception {
		// get param
		String cd = request.getParameter("cd");
		codeVO.setCd(cd);
		System.out.println("cd : " + cd);
		
		// delete
		codeService.deleteCodeVO(codeVO);
		
		return "redirect:/ManageCode.do";
	}	
	
	@RequestMapping(value = "CompleteSave.do")
	public String CompleteSave(@ModelAttribute("taskDataVO") TaskDataVO taskDataVO) throws Exception {
		// update complete status
		taskDataService.updateYNState(taskDataVO);		
		
		// if all checked -> task complete
		if (taskDataVO.getIsAllChecked().trim().equals("true")) {
			System.out.println("is all checked");
			String seqString = (taskDataVO.getSeq()).toString();
			System.out.println("seqStr : " + seqString);
			TaskDataVO tempVO = taskDataService.getTaskDataBySeq(seqString);
			System.out.println("vo : " + tempVO);
			taskService.updateTaskFinishByData(tempVO);
			
			List<TaskVO> taskList = taskService.getTaskListById(tempVO.getId());
			
			int result = 0;
			
			for(int i = 0; i < taskList.size(); ++i) {
				if (taskList.get(i).getState_cd().trim().equals("E3")) 
					++result;
			}
			
			System.out.println("result : "+result);
			System.out.println(tempVO.getId());
			
			if (result == taskList.size()) {
				System.out.println("in");
				activityService.updateActFinishById(tempVO.getId());				
			}
		}		
		
		
		
		return "redirect:/ReservedSafeManagement.do";
	}
	
	// 그룹 코드 관리
	@RequestMapping(value = "ManageGroupCode.do")
	public String ManageGroupCode(
			@ModelAttribute("codeSearchVO") CodeSearchVO codeSearchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception {
		System.out.println("search nm : " + codeSearchVO.getSearch_nm());
		
		/** paging setting */
		PaginationInfo paginationInfo = new PaginationInfo();		
		
		String pageNoStr = request.getParameter("currentPageNo");
		String currentSelected = request.getParameter("currentSelected");
		String currentSearch = request.getParameter("currentSearch");
		System.out.println("selected : " + currentSelected);
		
		if(codeSearchVO.getSearch_nm() == null || codeSearchVO.getSearch_nm().equals(""))
			codeSearchVO.setSearch_nm(currentSearch);
		
		int pageNo = 1;
		
		if(pageNoStr != null && !pageNoStr.equals(""))
			pageNo = Integer.parseInt(pageNoStr);

		System.out.println("pageNo : " + pageNo);
		
		List<GroupCodeVO> groupCodeList = null;		
		
		System.out.println(codeSearchVO.getSearch_nm());
		
		if(codeSearchVO.getSearch_nm() == null || codeSearchVO.getSearch_nm().equals("")) {
			System.out.println("??????????");
			groupCodeList = groupCodeService.getAllGroupCodeList();
			System.out.println(groupCodeList.get(0));
		}
		else {
			groupCodeList = groupCodeService.getGroupCodeListByName(codeSearchVO);
		}
		
		System.out.println("??");
		System.out.println("codeList size" + groupCodeList.size());
		
		int listCnt = groupCodeList.size(); // 총 목록 갯수
		int pageSize = 10; // 한 화면에 보여줄 페이지 갯수
		
		paginationInfo.setTotalRecordCount(listCnt);
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(codeSearchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(pageSize);	
		
		System.out.println("???");

		int start = (pageNo - 1) * 10;
		int end = start + paginationInfo.getRecordCountPerPage();
		if(end > listCnt)
			end = listCnt;
			
		System.out.println("codeList size" + groupCodeList.size());
		
		List<GroupCodeVO> viewList = groupCodeList.subList(start, end);

		model.addAttribute("codeList", viewList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "./main/ManageGroupCode";
	}
	
	// group code insert page
	@RequestMapping(value = "GroupCode_Insert.do")
	public String GroupCode_Insert(@ModelAttribute("groupCodeVO") GroupCodeVO groupCodeVO) throws Exception {
		return "./main/GroupCode_Insert";
	}
	
	// group code modify page
	@RequestMapping(value = "GroupCode_Modify.do")
	public String GroupCode_Modify(@ModelAttribute("groupCodeVO") GroupCodeVO groupCodeVO, ModelMap model, HttpServletRequest request) throws Exception {
		// search
		String cd = request.getParameter("cd");
		GroupCodeVO grpCodeVO = groupCodeService.getGroupCodeVO(cd);
		
		// view
		model.addAttribute("modCode", grpCodeVO);
		
		return "./main/GroupCode_Modify";
	}
	
	// group code insert action
	@RequestMapping(value = "GroupCode_Insert_Action.do")
	public String GroupCode_Insert_Action(@ModelAttribute("groupCodeVO") GroupCodeVO groupCodeVO) throws Exception {
		// do insert
		groupCodeService.insertGroupCode(groupCodeVO);
		
		return "redirect:/ManageGroupCode.do";
	}
	
	// group code modify action
	@RequestMapping(value = "GroupCode_Modify_Action.do")
	public String GroupCode_Modify_Action(@ModelAttribute("groupCodeVO") GroupCodeVO groupCodeVO) throws Exception {
		// do modify
		groupCodeService.updateGroupCode(groupCodeVO);
		
		return "redirect:/ManageGroupCode.do";
	}
	
	// group code modify action
	@RequestMapping(value = "GroupCode_Delete_Action.do")
	public String GroupCode_Delete_Action(@ModelAttribute("groupCodeVO") GroupCodeVO groupCodeVO, HttpServletRequest request) throws Exception {
		// do delete
		String cd = request.getParameter("cd");
		groupCodeVO.setCd(cd);
		groupCodeService.deleteGroupCode(groupCodeVO);

		return "redirect:/ManageGroupCode.do";
	}
	
	// activity finish
	@RequestMapping(value = "Activity_Finish.do")
	public String Activity_Finish(@ModelAttribute("activityVO") ActivityVO activityVO) throws Exception {
		// finish task
		ListsearchVO listsearchVO = new ListsearchVO();
		listsearchVO.setFindCode(activityVO.getIncdt_actvt_type_cd());
		listsearchVO.setIncdt_idtf_cd(activityVO.getIncdt_idtf_cd());
		listsearchVO.setDate(activityVO.getActvt_date());
		List<TaskVO> taskList = taskService.getTaskListVO(listsearchVO);
		
		for(int i = 0; i < taskList.size(); ++i) {
			taskList.get(i).setState_cd("E3");
			taskService.updateTaskFinish(taskList.get(i));
		}
		
		// finish act
		activityVO.setState_cd("E3");
		activityService.finishActivity(activityVO);
		
		return "redirect:/ReservedSafeManagement.do";
	}
	
	// show request act detail
	@RequestMapping(value = "RequestActivity_Detail.do")
	public String RequestActivity_Detail(@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, HttpServletRequest request, ModelMap model) throws Exception {
		// load from database
		String seq = request.getParameter("seq");
		System.out.println("seq : " + seq);
		
		RequestActivityVO rqstActVO = requestActivityService.getRqstActBySeq(seq);
		
		// view
		model.addAttribute("rqstAct", rqstActVO);
		
		return "./main/RequestActivity_Detail";
	}
	
	// show request chk detail
	@RequestMapping(value = "RequestChecklist_Detail.do")
	public String RequestChecklist_Detail(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		// load from database
		String seq = request.getParameter("seq");
		System.out.println("seq : " + seq);

		RequestChecklistVO rqstChkVO = requestChecklistService.getRqstChkBySeq(seq);

		// view
		model.addAttribute("rqstChk", rqstChkVO);

		return "./main/RequestChecklist_Detail";
	}
	
	// popup new request window
	@RequestMapping(value = "Popup_ListCnt.do")
	public String Popup_ListCnt() throws Exception {
		return "./main/Popup_ListCnt";
	}
	
	@RequestMapping(value = "EnderCommanderGuide_InsertAll.do")
	public String EnderCommanderGuide_InsertAll(HttpServletRequest request, ModelMap model) throws Exception {
		
		String guidnc_1 = (String) request.getParameter("guidnc_1");
		String guidnc_2 = (String) request.getParameter("guidnc_2");
		String guidnc_3 = (String) request.getParameter("guidnc_3");
		String Name = (String) request.getParameter("Name");
		String Date = (String) request.getParameter("Date");
		String id = (String) request.getParameter("id");
		
		TaskDataVO taskData = new TaskDataVO();
		
		taskData.setGuidnc_1(guidnc_1);
		taskData.setGuidnc_2(guidnc_2);
		taskData.setGuidnc_3(guidnc_3);
		taskData.setId(id);
		
		taskDataService.updateCommanderGuideById(taskData);
		
		model.addAttribute("returnUrl", "EnterCommanderGuide.do?Name="+Name+"&Date="+Date);
		model.addAttribute("RST", "CommandModifyOK");
		return "message";
	}
}
