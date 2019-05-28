package egovframework.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
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
import egovframework.main.service.ClsService;
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
import egovframework.main.service.VO.ClsVO;
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
import egovframework.main.util.DateUtil;
import egovframework.main.util.NetworkState;
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
	
	@Resource(name = "clsService")
	private ClsService clsService;

	// 메인
	@RequestMapping(value = "index.do")
	public String index(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpServletRequest request, HttpSession httpSession, ModelMap model,
			HttpServletResponse response) throws Exception {	
		if(httpSession.getAttribute("loginedUser") != null) {
			String athrt = (String) httpSession.getAttribute("SS_ATHRT");
			System.out.println("logined : " + ((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
			if(athrt.trim().equals("B1")) {
				// for B1
				// get activity list, request activity list, request fix checklist
				//List<ActivityVO> actList = activityService.getActivityListByLogined(((UserVO)httpSession.getAttribute("loginedUser")));
				List<TaskVO> taskList = taskService.getTaskListForMain(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
				System.out.println("taskList : " + taskList);
				//List<RequestActivityVO> reqActList = requestActivityService.getRequestedListForMain(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
				//List<RequestChecklistVO> reqCheckList = requestChecklistService.getRequestedListForMain(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
				
				model.addAttribute("taskList", taskList);
				//model.addAttribute("reqActList", reqActList);
				//model.addAttribute("reqCheckList", reqCheckList);
			}
			else if(athrt.trim().equals("B2")) {
				// for B2
				// get rqst ugcylist, rqst occlist, rqst checklist
				List<CheckApprovalVO> approList = checkApprovalService.getLimitCheckApprovalList(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
				List<RequestActivityVO> chkActList = requestActivityService.getLimitReqActList(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
				List<RequestChecklistVO> chkCheckList = requestChecklistService.getLimitReqCheckist(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
				
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
			HttpServletRequest request, HttpSession httpSession)
			throws Exception {

		if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
		}
		
		String doSearch = (String) request.getParameter("do");
		String actvt_date = (String) request.getParameter("date");
		String search_nm = (String) request.getParameter("search_nm");
		String state_cd = (String) request.getParameter("state_cd");
		
		TaskVO taskVO = new TaskVO();
		taskVO.setActvt_date(actvt_date);
		taskVO.setIncdt_actvt_type_cd_nm(search_nm);
		taskVO.setState_cd(state_cd);
		taskVO.setTask_psnchnrg_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		
		List<TaskVO> tempTaskList = null;
		
		if (doSearch != null) {
			if (doSearch.equals("1"))
				tempTaskList = taskService.getTaskListByCondition(taskVO);
			
			if (!(actvt_date.equals(""))) {
				mod.addAttribute("curYear", actvt_date.substring(0, 4));
				
				if(actvt_date.substring(4, 5).equals("0"))
					mod.addAttribute("curMon", actvt_date.substring(5, 6));
				else
					mod.addAttribute("curMon", actvt_date.substring(4, 6));
				
				if(actvt_date.substring(6, 7).equals("0"))
					mod.addAttribute("curDay", actvt_date.substring(7, 8));
				else
					mod.addAttribute("curDay", actvt_date.substring(6, 8));
			}			
			
			mod.addAttribute("curSearch_nm", search_nm);
			mod.addAttribute("curSelected_state", state_cd);
		}
		else {
			// 사용자 페이지에선 오늘 날짜 셋팅 x
			//mod.addAttribute("curYear", DateUtil.getCurrentYear());
			//mod.addAttribute("curMon", DateUtil.getCurrentMonth());
			//mod.addAttribute("curDay", DateUtil.getCurrentDay());
			
			tempTaskList = taskService.getTaskListVO(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		}		
		
		// paging
		int pageNo = 1; // default

		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);

		if (pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);

		Pagination pagination = new Pagination();
		pagination.setCountPerPage(8);
		pagination.setPaginationInfo(tempTaskList, pageNo);

		mod.addAttribute("taskList", pagination.getListPerPage(tempTaskList));
		mod.addAttribute("paginationInfo", pagination.getPaginationInfo());

		

		return "./main/ReservedSafeManagement";
	}

	// 수시 안전관리활동
	@RequestMapping(value = "OccasionalSafeManagement.do")
	public String OccasionalSafeManagement(HttpSession httpSession, HttpServletRequest request, ModelMap mod) throws Exception {
		
		String doSearch = (String) request.getParameter("do");
		String actvt_date = (String) request.getParameter("date");
		String search_nm = (String) request.getParameter("search_nm");
		String state_cd = (String) request.getParameter("state_cd");
		
		RequestActivityVO requestActivityVO = new RequestActivityVO();
		requestActivityVO.setActvt_date(actvt_date);
		requestActivityVO.setIncdt_actvt_type_cd_nm(search_nm);
		requestActivityVO.setState_cd(state_cd);
		requestActivityVO.setRqstr_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		
		List<RequestActivityVO> requestList = null;
		
		if (doSearch != null) {
			if (doSearch.equals("1"))
				requestList = requestActivityService.getListByCondition(requestActivityVO);
			
			if (!(actvt_date.equals(""))) {
				mod.addAttribute("curYear", actvt_date.substring(0, 4));
			
				if(actvt_date.substring(4, 5).equals("0"))
					mod.addAttribute("curMon", actvt_date.substring(5, 6));
				else
					mod.addAttribute("curMon", actvt_date.substring(4, 6));
			
				if(actvt_date.substring(6, 7).equals("0"))
					mod.addAttribute("curDay", actvt_date.substring(7, 8));
				else
					mod.addAttribute("curDay", actvt_date.substring(6, 8));
			}
			
			mod.addAttribute("curSearch_nm", search_nm);
			mod.addAttribute("curSelected_state", state_cd);
		}
		else {
			//mod.addAttribute("curYear", DateUtil.getCurrentYear());
			//mod.addAttribute("curMon", DateUtil.getCurrentMonth());
			//mod.addAttribute("curDay", DateUtil.getCurrentDay());
			
			requestList = requestActivityService.getRequestActivityListVO(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		}				
		
		// do trim()
		if (requestList != null) {
			for(int i = 0; i < requestList.size(); ++i) {
				requestList.get(i).setState_cd(requestList.get(i).getState_cd().trim());
			}
		}	
		
		// paging
		int pageNo = 1; // default
		
		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);
		
		if(pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);

		Pagination pagination = new Pagination();
		pagination.setCountPerPage(8);
		pagination.setPaginationInfo(requestList, pageNo);
		
		mod.addAttribute("requestList", pagination.getListPerPage(requestList));
		mod.addAttribute("paginationInfo", pagination.getPaginationInfo());

		return "./main/OccasionalSafeManagement";
	}

	// 체크리스트
	@RequestMapping(value = "CheckListManagement.do")
	public String CheckListManagement(HttpSession httpSession, HttpServletRequest request, ModelMap model) throws Exception {
		
		String doSearch = (String) request.getParameter("do");
		String rqst_date = (String) request.getParameter("date");
		String search_nm = (String) request.getParameter("search_nm");
		String state_cd = (String) request.getParameter("state_cd");
		
		RequestChecklistVO requestChecklistVO = new RequestChecklistVO();
		requestChecklistVO.setRqst_date(rqst_date);
		requestChecklistVO.setCtlg_nm(search_nm);
		requestChecklistVO.setState_cd(state_cd);
		requestChecklistVO.setRqstr_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		
		List<RequestChecklistVO> tempRequestedList = null;
		
		if (doSearch != null) {
			if (doSearch.equals("1"))
				tempRequestedList = requestChecklistService.getListByCondition(requestChecklistVO);
			
			if (!(rqst_date.equals(""))) {
				model.addAttribute("curYear", rqst_date.substring(0, 4));
			
				if(rqst_date.substring(4, 5).equals("0"))
					model.addAttribute("curMon", rqst_date.substring(5, 6));
				else
					model.addAttribute("curMon", rqst_date.substring(4, 6));
				
				if(rqst_date.substring(6, 7).equals("0"))
					model.addAttribute("curDay", rqst_date.substring(7, 8));
				else
					model.addAttribute("curDay", rqst_date.substring(6, 8));
			}
			
			model.addAttribute("curSearch_nm", search_nm);
			model.addAttribute("curSelected_state", state_cd);
		}
		else {
			//model.addAttribute("curYear", DateUtil.getCurrentYear());
			//model.addAttribute("curMon", DateUtil.getCurrentMonth());
			//model.addAttribute("curDay", DateUtil.getCurrentDay());
			
			tempRequestedList = requestChecklistService.getRequestedList(((UserVO)httpSession.getAttribute("loginedUser")));
		}				
		
		// paging
		int pageNo = 1; // default

		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);

		if (pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);

		Pagination pagination = new Pagination();
		pagination.setCountPerPage(8);
		pagination.setPaginationInfo(tempRequestedList, pageNo);	

		// view
		model.addAttribute("requestedList", pagination.getListPerPage(tempRequestedList));
		model.addAttribute("paginationInfo", pagination.getPaginationInfo());
		
		return "./main/CheckListManagement";
	}

	// 부대활동 모니터링
	@RequestMapping(value = "TroopsMonitoring.do")
	public String TroopsMonitoring(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, ModelMap model, HttpSession httpSession,
			RedirectAttributes redirectAttributes)
			throws Exception {
		Object obj = httpSession.getAttribute("loginedUser");
		String athrt = (String) httpSession.getAttribute("SS_ATHRT");
		
		if(obj != null) {
			// 최고지휘관 권한일 경우 (모니터링만 가능)
			if(athrt.trim().equals("B4")) {
				//List<CodeVO> idtfList = codeService.getCodeListByDvs("L2");	// 부대식별코드, 부대명 불러오기
				//model.addAttribute("idtfList", idtfList);
			}
		}	
		
		if(listsearchVO.getDate().equals("")) {
			model.addAttribute("curYear", DateUtil.getCurrentYear());
			model.addAttribute("curMon", DateUtil.getCurrentMonth());
			model.addAttribute("curDay", DateUtil.getCurrentDay());
			
			String year = Integer.toString(DateUtil.getCurrentYear());
			String month = Integer.toString(DateUtil.getCurrentMonth());
			String day = Integer.toString(DateUtil.getCurrentDay());
			
			if (DateUtil.getCurrentMonth() < 10) {
				month = "0" + month;
			}
			if (DateUtil.getCurrentDay() < 10) {
				day = "0" + day;
			}

			String curDate = year + month + day;
			System.out.println("curDate : " + curDate);
			
			return "redirect:/TroopsMonitoring_Search.do?incdt_idtf_cd=&date=" + curDate;
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
			
			if(listsearchVO.getIncdt_idtf_cd() != null && !(listsearchVO.getIncdt_idtf_cd().equals(""))) {
				CodeVO idtfVO = codeService.getCodeVO(listsearchVO.getIncdt_idtf_cd());
				
				model.addAttribute("curIdtfName", idtfVO.getCd_nm());
			}			
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
	   System.out.println("MainController.java > AssignTask.do > request.Name : "+ request.getParameter("Name"));
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
	   membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
	   
	   model.addAttribute("membersearchVO", membersearchVO);
	   
	   return "./main/AssignTask";
   }
   
   // Search&Listing Task Large cls
   @SuppressWarnings("unchecked")
@RequestMapping(value="Task_Large_input.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String Task_Large_input(
		   	@ModelAttribute("taskVO") TaskVO taskVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request,
			ModelMap model,
			HttpSession httpSession)throws Exception{
	   
	   List<TaskVO> taskLarge = taskService.getTaskLarge(membersearchVO);
	   int length = taskLarge.size();
	   
	   JSONArray jaTask = new JSONArray();
	   
	   if(length > 0 )
	   {
		   for(int i=0; i<length; i++)
		   {
			   JSONObject joTask = new JSONObject();
			   
			   joTask.put("cd", taskLarge.get(i).cd);
			   joTask.put("cd_nm", taskLarge.get(i).cd_nm);
			   
			   jaTask.add(joTask);
		   }
	   }
	   else
		{
			JSONObject joTask = new JSONObject();

			joTask.put("cd", "E1");
			joTask.put("cd_nm", "데이터가 없습니다.");

			
			jaTask.add(joTask);
			
		}
	   
	   	String jsonResult = jaTask.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println(jsonResult);

		return jsonResult; //ajax의 result로 리턴됨
   }
   
   @SuppressWarnings("unchecked")
@RequestMapping(value="AssignTask_Ajax.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String AssignTaskAjax(
		   	@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession, 
			ModelMap model)
   throws Exception{
	   String Name = request.getParameter("Name");
//	   String Date = request.getParameter("Date");
	   
	   String jsonString = request.getParameter("jsonString");
	   System.out.println("jsonString = :"+jsonString);
	   	
	   jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
	   	
	   JSONParser parser = new JSONParser();
	   
	   JSONObject json = (JSONObject) parser.parse(jsonString);
	   
	   String searchCd1 	= (String) json.get("Code");
	   String Date	 		= (String) json.get("Date");
	   String lrgcls		= (String) json.get("lrgcls");
	   String mdcls 		= (String) json.get("mdcls");
	   membersearchVO.setSearchCondition1(searchCd1);
	   membersearchVO.setSearchCondition2(Date);
	   membersearchVO.setSearchCondition3(lrgcls);
	   membersearchVO.setSearchCondition(mdcls);
	   membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
	   
	   List<TaskVO> taskList = taskService.getTaskList(membersearchVO);
	   model.addAttribute("taskList", taskList);
	   
//	   List<CheckCodeVO> checkCodeList = checkCodeService.getCheckList(membersearchVO);
//	   model.addAttribute("checkCodeList", checkCodeList);
	   
	   model.addAttribute("membersearchVO", membersearchVO);
	   
	   int length = taskList.size();
	   
	   JSONArray jaTask = new JSONArray();
	   if(length > 0)
	   {
	   for(int i=0;i<length;i++){
		   JSONObject joTask = new JSONObject();
		   
		   joTask.put("task", 					taskList.get(i).task);
		   joTask.put("incdt_idtf_cd", 			taskList.get(i).incdt_idtf_cd);
		   joTask.put("task_psnchnrg_rnk", 		taskList.get(i).task_psnchnrg_rnk);
		   joTask.put("task_psnchnrg_srvno", 	taskList.get(i).task_psnchnrg_srvno);
		   joTask.put("lrgcls_nm", 				taskList.get(i).lrgcls_nm);
		   joTask.put("mdcls_nm", 				taskList.get(i).mdcls_nm);
		   joTask.put("incdt_actvt_type_cd", 	taskList.get(i).incdt_actvt_type_cd);
		   joTask.put("seq", 					taskList.get(i).getSeq());
		   
		   jaTask.add(joTask);
	   }
	   }
	   else
	   {
		   JSONObject joTask = new JSONObject();
		   
		   joTask.put("incdt_actvt_type_cd","E001");
		   
		   jaTask.add(joTask);
	   }
	   String jsonResult = jaTask.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println(jsonResult);

		return jsonResult; //ajax의 result로 리턴됨
   }
//   Request AssignTask_insert
   @RequestMapping(value="AssignTask_insert.do")
   public String AssignTaskInsert(
		   @ModelAttribute("memberVO") MemberVO memberVO,
		   @ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO,
		   HttpServletRequest request, 
		   HttpSession httpSession, 
		   ModelMap model
		   ) throws Exception {
		request.setCharacterEncoding("utf-8");
		String Name 	= request.getParameter("Name");
		String Date 	= request.getParameter("Date");
		
		String Large 	= request.getParameter("Large");
		String Middle 	= request.getParameter("Middle");
		String Small 	= request.getParameter("Small");
		
		membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());

		List<MemberVO> memberList = memberService.getMemberList(membersearchVO);

		model.addAttribute("Name", Name);
		model.addAttribute("Date", Date);
		model.addAttribute("memberList", memberList);

		return "./main/AssignTask_insert";
	}
	@RequestMapping(value="AssignTask_Delete.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String AssignTask_Delete(
				@ModelAttribute("taskVO") TaskVO taskVO,
				HttpServletRequest request,
				HttpServletResponse response,
				HttpSession httpSession,
				ModelMap model)throws Exception{
		
		String jsonString = request.getParameter("jsonString");
		String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		String seq =(String)json.get("seq");
		taskVO.setSeq(seq);
		List<TaskVO> setSeq = taskService.selectId(taskVO);
		
		taskVO.setId(setSeq.get(0).getId());
		taskVO.setTask_psnchnrg_srvno(setSeq.get(0).getTask_psnchnrg_srvno());
		taskVO.setActvt_date(setSeq.get(0).getTask_psnchnrg_srvno());
		taskService.deleteTask(taskVO);
		return "";
	}
	// 입력 완료 창
	@RequestMapping(value = "Task_Insert_Result.do")
	public String TaskResult(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("taskVO") TaskVO taskVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
			HttpServletRequest request, 
			HttpSession httpSession, 
			ModelMap model
			) throws Exception {
		HttpSession httpsession 	= request.getSession();
		String 	incdt_idtf_cd 		=((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
		String 	incdt_actvt_type_cd = request.getParameter("INCDT_ACTVT_TYPE_CD");
		String 	actvt_date 			= request.getParameter("ACTVT_DATE");
		String 	task 				= request.getParameter("Task");
		String 	task_psnchnrg_srvno = request.getParameter("TASK_PSNCHNRG_SRVNO");
		String 	seq	 				= request.getParameter("taskSeq");
		Integer Seq					= Integer.parseInt(seq);
		String 	Large				= request.getParameter("Large");
		String	Middle				= request.getParameter("Middle");
		String 	Small				= request.getParameter("Small");
		
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
			model.addAttribute("returnUrl", "AssignTask_insert.do?Name="+incdt_actvt_type_cd+"&Date="+actvt_date);
			model.addAttribute("RST", "TaskInsertFailed");			
		}
		else
		{
			List<TaskVO> ActivityList = taskService.getActivityRequire(taskVO);
			if(ActivityList.size() == 0)
			{
				taskService.InsertTask(taskVO);
			}
			else
			{
				taskVO.setId(ActivityList.get(0).getId());
				taskService.InsertActivity(taskVO);
			}
			incdt_actvt_type_cd = URLEncoder.encode(incdt_actvt_type_cd,"UTF-8");
		model.addAttribute("returnUrl", "AssignTask.do?Name="+incdt_actvt_type_cd+"&Date="+actvt_date+"&Large="+Large+"&Middle="+Middle+"&Small="+Small+"&search=yes");
		model.addAttribute("RST", "TaskInsertOK");
		}	 
		return "message";
		
	}
	
//	Check New Task 
	  @RequestMapping(value="TaskInsert_already_Check_Ajax_json.do", produces="text/html; charset=utf-8")
	   @ResponseBody
	   public String TaskInsert_already_Check_Ajax_json(
				@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
				@ModelAttribute("taskVO") TaskVO taskVO,
				@ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
			   HttpServletRequest request,
			   HttpServletResponse response,
			   HttpSession httpSession, 
			   ModelMap model
			   ) throws Exception {
		   	String jsonString = request.getParameter("jsonString");
			String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
			
			jsonString = jsonString.replaceAll("&quot;", "\""); 
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(jsonString);

			String Task 		= (String) json.get("Task");
			String Chargesrvno 	= (String) json.get("Chargesrvno");
			String TaskName 	= (String) json.get("TaskName");
			String TaskDate 	= (String) json.get("TaskDate");
			String Seq 			= (String) json.get("TaskSeq");
//			int TaskSeq 		= (int) json.get("TaskSeq");
			
			taskVO.setIncdt_idtf_cd(incdt);
			taskVO.setIncdt_actvt_type_cd(TaskName);
			taskVO.setActvt_date(TaskDate);
			taskVO.setTask(Task);
			taskVO.setTask_psnchnrg_srvno(Chargesrvno);
			taskVO.setSeq(Seq);
			
			List<TaskVO> taskoverlap = taskService.getTaskSelected(taskVO);
			String result = "";
			int length = taskoverlap.size();
			if(length > 0)
			{
				result = "이미 과업이 등록된 사람입니다.";
			}
			else
			{
				result = "ok";
			}
			return result;
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
			
//			List<CheckApprovalVO> ApprovalList = checkApprovalService.getCheckApprovalList(membersearchVO);
			
			String actvt_date 	= request.getParameter("actvt_date");
			String rqst_date 	= request.getParameter("rqst_date");
			String rqstr_srvno	= request.getParameter("rqstr_srvno");
			String actvt_type	= request.getParameter("actvt_type");
			String state_select	= request.getParameter("state_select");
			
			membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
			checkApprovalVO.setActvt_date(actvt_date);
			checkApprovalVO.setRqst_date(rqst_date);
			checkApprovalVO.setRqstr_nm(rqstr_srvno);
			checkApprovalVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
			checkApprovalVO.setIncdt_actvt_type_cd(actvt_type);
			checkApprovalVO.setState_cd(state_select);
			List<CheckApprovalVO> ApprovalList = checkApprovalService.searchApproval(checkApprovalVO); 
			
			
			//paging 
			int pageNo = 1; 	//default
			
			String pageNoStr = (String) request.getParameter("currentPageNo");
			System.out.println("PageNoStr : " + pageNoStr);
			
			if(pageNoStr != null && !(pageNoStr.equals("")))
				pageNo = Integer.parseInt(pageNoStr);
			
			Pagination pagination = new Pagination();
			pagination.setPaginationInfo(ApprovalList, pageNo);
			
			Calendar oCalendar = Calendar.getInstance( );
				
			String Date ="";
			String rqstDate="";
			
			int Year = oCalendar.get(Calendar.YEAR);
			int Month = oCalendar.get(Calendar.MONTH) + 1;
			int Day = oCalendar.get(Calendar.DAY_OF_MONTH); 
			Date = Year + "-" +Month+"-"+Day;
			   
			SimpleDateFormat realDate = new SimpleDateFormat("yyyy-MM-dd");
			
			Date  realDate1 = realDate.parse(Date);
			if(actvt_date != null)
			{
				Date =actvt_date;
				
			}
			else if(rqstDate != null)
			{
				rqstDate = rqst_date;
			}
			else
			{
				Date = realDate.format(realDate1);
				rqstDate = realDate.format(realDate1);

			}

			   
			model.addAttribute("Date", Date);
			model.addAttribute("rqstDate", rqstDate);
			model.addAttribute("ApprovalList", pagination.getListPerPage(ApprovalList));
			model.addAttribute("paginationInfo", pagination.getPaginationInfo());
			model.addAttribute("CheckApproval", "yes");
			   
				   return "./main/CheckApproval";
			  }
   
   //검색개능 Ajax 통신
   @SuppressWarnings("unchecked")
@RequestMapping(value="CheckApproval_search_AJAX.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String CheckApproval_search_AJAX(
		   @ModelAttribute("CheckApprovalVO") CheckApprovalVO checkApprovalVO,
	   		@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
		   HttpServletRequest request,
		   HttpServletResponse response,
		   HttpSession httpSession,
		   ModelMap model
		   ) throws Exception{
	   
	   String jsonString = request.getParameter("jsonString");
	   
	   jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
	   
	   JSONParser parser = new JSONParser();
	   JSONObject json = (JSONObject) parser.parse(jsonString);
	   
	   String actvt_date 	= (String) json.get("actvt_date");
	   String rqst_date 	= (String) json.get("rqst_date");
	   String rqstr_srvno 	= (String) json.get("rqstr_srvno");
	   String actvt_type 	= (String) json.get("actvt_type");
	   String state_select 	= (String) json.get("state_select");
	   
	   System.out.println(json);
	   
	   if(actvt_date.equals(""))
	   {
   
	   }
	   else
	   {
		   checkApprovalVO.setActvt_date(actvt_date);
	   }
	   if(rqst_date.equals(""))
	   {
		   
	   }
	   else
	   {
		   checkApprovalVO.setRqst_date(rqst_date);
	   }
	   if(rqstr_srvno.equals(""))
	   {
		   
	   }
	   else
	   {
		   checkApprovalVO.setRqstr_nm(rqstr_srvno);
	   }
	   checkApprovalVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
	   if(actvt_type .equals(""))
	   {
		   
	   }
	   else
	   {
		   checkApprovalVO.setIncdt_actvt_type_cd(actvt_type);
	   }
	   if(state_select .equals(""))
	   {
		   
	   }
	   else
	   {
		   checkApprovalVO.setState_cd(state_select);
	   }
	   List<CheckApprovalVO> TaskSearched = checkApprovalService.searchApproval(checkApprovalVO); 
			  
	   int length = TaskSearched.size();
	   JSONArray jaTask = new JSONArray();
	   if(length > 0)
	   {
		   for(int i=0;i<length;i++){
			   JSONObject joTask = new JSONObject();
			   
			   joTask.put("incdt_idtf_cd", TaskSearched.get(i).getIncdt_idtf_cd());
			   joTask.put("rqst_date", TaskSearched.get(i).getRqst_date());
			   joTask.put("rqstr_srvno", TaskSearched.get(i).getRqstr_srvno());
			   joTask.put("incdt_actvt_type_cd", TaskSearched.get(i).getIncdt_actvt_type_cd());
			   joTask.put("actvt_date", TaskSearched.get(i).getActvt_date());
			   joTask.put("ctlg_cd", TaskSearched.get(i).getCtlg_cd());
			   joTask.put("state_cd", TaskSearched.get(i).getState_cd());
			   joTask.put("seq", TaskSearched.get(i).getSeq());
			   
			   jaTask.add(joTask);
		   }
	   }
	   else
	   {
		   JSONObject joTask = new JSONObject();
		   
		   joTask.put("ctlg_cd", "E001_001");
		   
		   jaTask.add(joTask);
	   }
	   String jsonResult = jaTask.toString();
	   System.out.println(jsonResult);
	   return jsonResult;
   }
   // CheckApproval Insert Page
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
//   CheckApproval Insert Button Click result
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
	   checkApprovalVO = checkApprovalService.findApproveByKey(checkApprovalVO);
	   checkApprovalVO.setopn(opn);
	   
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
	   System.out.println("state : " + taskData.getState_cd());
	   
	   if(taskData.getCtlg_itm_cd_1() != null) {
		   if(taskData.getCtlg_itm_cd_1().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_1("N ");
	   }
	   if(taskData.getCtlg_itm_cd_2() != null) {
		   if(taskData.getCtlg_itm_cd_2().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_2("N ");
	   }
	   if(taskData.getCtlg_itm_cd_3() != null) {
		   if(taskData.getCtlg_itm_cd_3().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_3("N ");
	   }
	   if(taskData.getCtlg_itm_cd_4() != null) {
		   if(taskData.getCtlg_itm_cd_4().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_4("N ");
	   }
	   if(taskData.getCtlg_itm_cd_5() != null) {
		   if(taskData.getCtlg_itm_cd_5().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_5("N ");
	   }
	   if(taskData.getCtlg_itm_cd_6() != null) {
		   if(taskData.getCtlg_itm_cd_6().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_6("N ");
	   }
	   if(taskData.getCtlg_itm_cd_7() != null) {
		   if(taskData.getCtlg_itm_cd_7().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_7("N ");
	   }
	   if(taskData.getCtlg_itm_cd_8() != null) {
		   if(taskData.getCtlg_itm_cd_8().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_8("N ");
	   }
	   if(taskData.getCtlg_itm_cd_9() != null) {
		   if(taskData.getCtlg_itm_cd_9().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim()))
			   taskData.setCtlg_itm_yn_9("N ");
	   }
	   if(taskData.getCtlg_itm_cd_10() != null) {
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
		   HttpSession httpSession, 
		   ModelMap model) throws Exception
   {
	   
	   requestActivityVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
	   
	   
	   	String rqst_date 	= request.getParameter("rqst_date");
		String rqstr_srvno	= request.getParameter("rqstr_srvno");
		String actvt_type	= request.getParameter("actvt_type");
		String state_select	= request.getParameter("state_select");
		String actvt_date	= request.getParameter("actvt_date");
		
			   requestActivityVO.setActvt_date(actvt_date);
			   requestActivityVO.setRqst_date(rqst_date);
			   requestActivityVO.setRqstr_srvno(rqstr_srvno);
			   requestActivityVO.setIncdt_actvt_type_cd(actvt_type);
			   requestActivityVO.setState_cd(state_select);
		
		List<RequestActivityVO> RequestList = requestActivityService.searchApproval(requestActivityVO); 
		
		// paging
		int pageNo = 1; // default
			
		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);
				
		if(pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);
			
		Pagination pagination = new Pagination();
		pagination.setPaginationInfo(RequestList, pageNo);
		
		System.out.println(pagination.getListPerPage(RequestList).size());
		
		Calendar oCalendar = Calendar.getInstance( );
		
		String Date ="";
		String rqstDate="";
		
		int Year = oCalendar.get(Calendar.YEAR);
		int Month = oCalendar.get(Calendar.MONTH) + 1;
		int Day = oCalendar.get(Calendar.DAY_OF_MONTH); 
		Date = Year + "-" +Month+"-"+Day;
		   
		SimpleDateFormat realDate = new SimpleDateFormat("yyyy-MM-dd");
		
		Date  realDate1 = realDate.parse(Date);
		if(actvt_date != null)
		{
			Date =actvt_date;
			
		}
		else if(rqstDate != null)
		{
			rqstDate = rqst_date;
		}
		else
		{
			Date = realDate.format(realDate1);
			rqstDate = realDate.format(realDate1);

		}
		model.addAttribute("Date", 			Date);
		model.addAttribute("rqstDate",		rqstDate);
	   	
		model.addAttribute("rqstr_srvno	",	rqstr_srvno);
		model.addAttribute("actvt_type",	actvt_type);
		model.addAttribute("state_select",	state_select);
		
		model.addAttribute(requestActivityVO);
		model.addAttribute("RequestList", pagination.getListPerPage(RequestList));
		model.addAttribute("paginationInfo", pagination.getPaginationInfo());
			  
	   
//	   model.addAttribute("RequestList", RequestList);
	   
	   return "./main/CheckOccasionalSafeManagement";
   }
   @RequestMapping(value="CheckOccasionalSafeManagement_Select.do")
   public String CheckOccasionalSafeManagementSelect(
		   @ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
		   HttpServletRequest request,
		   HttpSession httpSession, 
		   ModelMap model) throws Exception
   {
	   String seq = request.getParameter("seq");
	   requestActivityVO.setSeq(seq);
	   requestActivityVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd());
	   List<RequestActivityVO> RequestList = requestActivityService.getRequestList(requestActivityVO);
	   requestActivityVO.setState_cd(RequestList.get(0).state_cd);
	   model.addAttribute(requestActivityVO);
	   model.addAttribute("RequestList", RequestList);
	   
	   return "/main/CheckOccasionalSafeManagement_Select";
   }
   
   @SuppressWarnings("unchecked")
@RequestMapping(value="CheckOccasional_search_AJAX", produces="text/html; charset=utf-8")
   @ResponseBody
   
   public String CheckOccasional_search_AJAX(
		   @ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO,
		   HttpServletRequest request,
		   HttpServletResponse response,
		   HttpSession httpSession, 
		   ModelMap model
		   )throws Exception{
	   
	   	String jsonString = request.getParameter("jsonString");		 //request를 사용하여 jsonString 저장
	   
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		   String actvt_date 	= (String) json.get("actvt_date");
		   String rqst_date 	= (String) json.get("rqst_date");
		   String rqstr_srvno 	= (String) json.get("rqstr_srvno");
		   String actvt_type 	= (String) json.get("actvt_type");
		   String state_select 	= (String) json.get("state_select");
		   
		   System.out.println(json);
		   
		   if(actvt_date.equals(""))
		   {
	   
		   }
		   else
		   {
			   requestActivityVO.setActvt_date(actvt_date);
		   }
		   if(rqst_date.equals(""))
		   {
			   
		   }
		   else
		   {
			   requestActivityVO.setRqst_date(rqst_date);
		   }
		   if(rqstr_srvno.equals(""))
		   {
			   
		   }
		   else
		   {
			   requestActivityVO.setRqstr_srvno(rqstr_srvno);
		   }
		   requestActivityVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		   if(actvt_type .equals(""))
		   {
			   
		   }
		   else
		   {
			   requestActivityVO.setIncdt_actvt_type_cd(actvt_type);
		   }
		   if(state_select .equals(""))
		   {
			   
		   }
		   else
		   {
			   requestActivityVO.setState_cd(state_select);
		   }
		   List<RequestActivityVO> TaskSearched = requestActivityService.searchApproval(requestActivityVO); 
				  
		   int length = TaskSearched.size();
		   JSONArray jaTask = new JSONArray();
		   if(length > 0)
		   {
			   for(int i=0;i<length;i++){
				   JSONObject joTask = new JSONObject();
				   
				   joTask.put("incdt_idtf_cd"		, TaskSearched.get(i).getIncdt_idtf_cd().trim());
				   joTask.put("actvt_date"			, TaskSearched.get(i).getActvt_date());
				   joTask.put("seq"					, TaskSearched.get(i).getSeq());
				   joTask.put("state_cd"			, TaskSearched.get(i).getState_cd().trim());
				   joTask.put("incdt_actvt_type_cd"	, TaskSearched.get(i).getIncdt_actvt_type_cd().trim());
				   joTask.put("rqst_date"			, TaskSearched.get(i).getRqst_date());
				   joTask.put("rqstr_srvno"			, TaskSearched.get(i).getRqstr_srvno());	
				   jaTask.add(joTask);
			   }
		   }
		   else
		   {
			   JSONObject joTask = new JSONObject();
			   
			   joTask.put("ctlg_cd", "E001_001");
			   
			   jaTask.add(joTask);
		   }
		   String jsonResult = jaTask.toString();
		   System.out.println(jsonResult);
		   return jsonResult;
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
				
					membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
					String searchCondition = request.getParameter("searchCondition");
					String searchKeyword = request.getParameter("searchvalue");
					
					membersearchVO.setSearchCondition(searchCondition);
					membersearchVO.setSearchKeyword(searchKeyword);
					
					// 190520 add by seungwon ////////////
					String searchAthrt = (String) request.getParameter("searchAthrt");
					membersearchVO.setSearchAthrt(searchAthrt);
					//////////////////////////////////////
					
					List<MemberVO> memberList;
					
					if(((UserVO)httpSession.getAttribute("loginedUser")).getathrt().trim().equals("B3"))
					{
						memberList = memberService.getManagerList(membersearchVO);
					}
					else
					{
						memberList = memberService.getMemberList(membersearchVO);
					}
					//paging 
					int pageNo = 1; 	//default
					
					String pageNoStr = (String) request.getParameter("currentPageNo");
					System.out.println("PageNoStr : " + pageNoStr);
					
					if(pageNoStr != null && !(pageNoStr.equals("")))
						pageNo = Integer.parseInt(pageNoStr);
					
					Pagination pagination = new Pagination();
					pagination.setPaginationInfo(memberList, pageNo);
					
					model.addAttribute("membersearchVO", membersearchVO);
					model.addAttribute("memberList", pagination.getListPerPage(memberList));
					model.addAttribute("paginationInfo", pagination.getPaginationInfo());
					
					

		return "./main/UserList";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="MemberSearch_AJAX.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String MemberSearchAjax(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession,
			ModelMap model)throws Exception
			{
			String jsonString = request.getParameter("jsonString");
			jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
			
			String SearchCondition = (String) json.get("searchCondition");
			String SearchKeyword = (String) json.get("searchvalue");
			
			membersearchVO.setSearchCondition(SearchCondition);
			membersearchVO.setSearchKeyword(SearchKeyword);
			membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
			
			List<MemberVO> memberList = memberService.getMemberList(membersearchVO);
			
			int length = memberList.size();
			
			JSONArray jaTask = new JSONArray();
			
			if( length > 0)
			{
				for(int i=0; i<length; i++)
				{
					JSONObject joTask = new JSONObject();
					joTask.put("chng_date", memberList.get(i).getchng_date());
					joTask.put("srvno", memberList.get(i).getSrvno());
					joTask.put("stmt", memberList.get(i).getStmt());
					joTask.put("cd_nm", memberList.get(i).getCd_nm());
					joTask.put("rspofc_nm", memberList.get(i).getRspofc_nm());
					
					jaTask.add(joTask);
				}
			}
			else
			{
				JSONObject joTask = new JSONObject();
				
				joTask.put("srvno","E001");
			}
			
			String jsonResult = jaTask.toString();
			return jsonResult;
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "Member_srvno_Ajax.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String Member_srvno_Ajax(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model
			)
	throws Exception
	{
		String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String srvno = (String) json.get("Srvno");
		srvno = srvno.trim();
		List<MemberVO> membersearched;
		
		memberVO.setSrvno(srvno);
		
		membersearched = memberService.getMemberselect(memberVO);
		
		int length = membersearched.size();
		
		JSONObject JoTask = new JSONObject();
		
		if(length == 0)
		{
			JoTask.put("result","ok");
		}
		else{
			JoTask.put("result","fail");
		}
		
		String jsonResult = JoTask.toString();
		System.out.println(jsonResult);
		
		return jsonResult;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "MemberInsert_Ajax.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String MemberInsert_Ajax(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession,
			ModelMap model
			)
	throws Exception{
		String jsonString = request.getParameter("jsonString");
		String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		String stmt 			= (String) json.get("stmt");
		String rspofc_nm 		= (String) json.get("rspofc_nm");
		String srvno 			= (String) json.get("srvno");
		String password 		= (String) json.get("password");
		String rnkcd_select		= (String) json.get("rnkcd_select");
		String password_check	= (String) json.get("password_check");		
		String prtbl_telno 		= (String) json.get("prtbl_telno");
		String incdt_idtf_cd 	= (String) json.get("incdt_idtf_cd");
		String athrt_select		= (String) json.get("athrt_select");
		String montr_select		= (String) json.get("montr_select");
		String passwordalert 	= (String) json.get("pwalert");
		String ip				= (String) json.get("ip");
		
		memberVO.setSrvno(srvno);
		memberVO.setPw(password);
		memberVO.setRnkcd(rnkcd_select);
		memberVO.setStmt(stmt);
		memberVO.setRspofc_nm(rspofc_nm);
		memberVO.setathrt(athrt_select);
		memberVO.setPrtbl_telno(prtbl_telno);
		memberVO.setIncdt_idtf_cd(incdt_idtf_cd);
		memberVO.setmontr(montr_select);
		
		// 190522 add by seungwon //
		memberVO.setIp(ip);
		
		membersearchVO.setSearchKeyword(request.getParameter("srvno"));
		List<MemberVO> memberList = memberService.getMemberselect(memberVO);
		JSONObject joTask = new JSONObject();
		
		if(memberList.size() > 0)
		{
			
			joTask.put("result","fail");
		}
		else
		{
			memberService.insertMember(memberVO);
			model.addAttribute("membersearchVO", membersearchVO);
			joTask.put("result","success");
		}
		
		String jsonResult = joTask.toString();
		return jsonResult;
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
		   				System.out.println(memberselect.get(0).getIp());
		   				model.addAttribute("memberselect", memberselect);
		   				
		   				
						return "./main/MemberModify";
					}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "MemberModify_Ajax.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String MemberModify_Ajax(
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			@ModelAttribute("memberVO") MemberVO memberVO,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession,
			ModelMap model
			)
	throws Exception{
		String jsonString = request.getParameter("jsonString");
		String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		String stmt 			= (String) json.get("stmt");
		String rspofc_nm 		= (String) json.get("rspofc_nm");
		String srvno 			= (String) json.get("srvno");
		String password 		= (String) json.get("password");
		String rnkcd_select		= (String) json.get("rnkcd_select");
		String password_check	= (String) json.get("password_check");		
		String prtbl_telno 		= (String) json.get("prtbl_telno");
		String incdt_idtf_cd 	= (String) json.get("incdt_idtf_cd");
		String athrt_select		= (String) json.get("athrt_select");
		String montr_select		= (String) json.get("montr_select");
		String passwordalert 	= (String) json.get("pwalert");
		String ip				= (String) json.get("ip");
		String acc_state_info 	= (String) json.get("acc_state_info");
		
		memberVO.setSrvno(srvno);
		memberVO.setPw(password);
		memberVO.setRnkcd(rnkcd_select);
		memberVO.setStmt(stmt);
		memberVO.setRspofc_nm(rspofc_nm);
		memberVO.setathrt(athrt_select);
		memberVO.setPrtbl_telno(prtbl_telno);
		memberVO.setIncdt_idtf_cd(incdt_idtf_cd);
		memberVO.setmontr(montr_select);
		
		// 190522 add by seungwon //
		memberVO.setIp(ip);
		
		membersearchVO.setSearchKeyword(request.getParameter("srvno"));
		
		if(memberVO.getPw().equals(""))
		{
			List<MemberVO> memberselect =memberService.getMemberselect(memberVO);
			
			memberVO.setPw(memberselect.get(0).getPw());
		}
		
		memberService.ModifyMember(memberVO);
		
		List<MemberVO> memberList = memberService.getMemberselect(memberVO);
		JSONObject joTask = new JSONObject();
		
		if(memberList.size() == 1)
		{
			
			joTask.put("result","success");
		}
		else
		{
			joTask.put("result","fail");
		}
		
		String jsonResult = joTask.toString();
		return jsonResult;
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
		memberVO.setacc_state_info(request.getParameter("acc_state_info"));
		
		// 190522 add by seungwon //
		memberVO.setIp((String) request.getParameter("comip"));
		////////////////////////////
		
		if(memberVO.getPw().equals(""))
		{
			List<MemberVO> memberselect =memberService.getMemberselect(memberVO);
			
			memberVO.setPw(memberselect.get(0).getPw());
		}
		
		memberService.ModifyMember(memberVO);
		 
		model.addAttribute("returnUrl", "Userlist.do");
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
	}
	//사용자 삭제
	@RequestMapping(value = "DeleteMember.do")
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
			
		   return "./main/SelectCheckListItem";
   }
   
   @SuppressWarnings("unchecked")
@RequestMapping(value="SelectChecklistItem_AJAX_JSON.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String SelectChecklistItem_AJAX_JSON(
		   HttpServletRequest request,
		   HttpServletResponse response,
		   ModelMap model,
		   HttpSession httpSession,
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO
		   )throws Exception{
	   
	   	String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String Code = (String) json.get("Code");
		
		sptlyCodeVO.setCtlg_cd(Code);
		
		List<SptlyCodeVO> taskDataList = sptlyCodeService.getCtlgDataList(sptlyCodeVO);
		
		int length = taskDataList.size();
		
		JSONArray jaTask = new JSONArray();
		if(length > 0)
		{
			for(int i=0;i<length;i++)
			{
				JSONObject joTask = new JSONObject();
				
				joTask.put("cd", taskDataList.get(i).getCd().trim());
				joTask.put("cd_nm",taskDataList.get(i).getCd_nm().trim());
				jaTask.add(joTask);
			}
		}
		else
		{
			JSONObject joTask = new JSONObject();
			joTask.put("cd", "E001");
			joTask.put("cd_nm","데이터가 없습니다.");
			jaTask.add(joTask);
		}
		
		String jsonResult = jaTask.toString();
		
	   return jsonResult;
   }
   
// SelectChecklistEvent Preset Select Ajax Function
   
@SuppressWarnings("unchecked")
@RequestMapping(value="SelectChecklistItem_ajax.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String SelectChecklistAjax(
		   HttpServletRequest request, HttpServletResponse response, Model model, HttpSession httpSession, 
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO
		   )throws Exception
   {
		// get Incdt Code from Login Session
	   String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
	   
	   // Get Data From Ajax post parameter
	   String seq = request.getParameter("code");
	   String jsonString = request.getParameter("jsonString");
	   String Task = request.getParameter("Task");
	   
	   // replace charmap at Ajax json String 
	   jsonString = jsonString.replaceAll("&quot;", "\"");
	   
	   //parse json String
	   JSONParser parser = new JSONParser();
	   JSONObject json = (JSONObject) parser.parse(jsonString);
	   
	   //put Vo data
	   sptlyCodeVO.setTask_cd(seq);
	   sptlyCodeVO.setIncdt_idtf_cd(incdt);
	   sptlyCodeVO.setCtlg_cd(Task);
	   
	   checklistItemVO.setCtlg_cd(Task);
	   checklistItemVO.setCtlg_itm_cd(seq);
	   
	   //search Data with Vo data
	   List<SptlyCodeVO>TaskSearched = sptlyCodeService.getActivityCtlgList(sptlyCodeVO);

	   
	   JSONArray jaTask = new JSONArray();
	   JSONArray JaPre	= new JSONArray();
	   
	   // devide cltg list to ArrayList
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
			   // put list to Json Object
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
		   }
	   }
	   
	   List<ChecklistItemVO> Checklist = checklistItemService.ChecklistSelect(checklistItemVO);
	   
	   int lengthCheck = Checklist.size();
	 
	   if(lengthCheck > 0)
	   {
		   for(int i=0;i<lengthCheck;i++)
		   {
			   JSONObject joTask = new JSONObject();
			   
			   joTask.put("ctlg_cd", Checklist.get(i).ctlg_cd);
			   joTask.put("ctlg_itm_cd", Checklist.get(i).ctlg_itm_cd);
			   joTask.put("ctlg_itm_ctnt", Checklist.get(i).ctlg_itm_ctnt);
			   joTask.put("stdd_yn", Checklist.get(i).stdd_yn);
			   joTask.put("prtcuse_frqc", Checklist.get(i).prtcuse_frqc);
	
			   JaPre.add(joTask);
		   }
	   }
	   JSONObject ResultJson = new JSONObject();
	   
	   ResultJson.put("Selected", jaTask);
	   ResultJson.put("Preset", JaPre);
	   
	   String jsonResult = ResultJson.toString();
	   
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
	   checklistVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
	   checklistService.Checklistreset(checklistVO);
	   
	   sptlyCodeVO.setCtlg_cd(name);
	   sptlyCodeVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
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
	   name = URLEncoder.encode(name,"UTF-8");
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
	   String srvno = ((UserVO)httpSession.getAttribute("loginedUser")).getSrvno();
	   String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
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
	   sptlyCodeVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
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
	   name = URLEncoder.encode(name,"UTF-8");
		model.addAttribute("returnUrl", "SelectCheckListItem.do?ctlg_cd="+name+"&task="+Task_seq);
		 model.addAttribute("RST", "ApproveOK");
		 return "message";
		   }
   @RequestMapping(value="SelectCheckList_Ajax_JSON.do", produces="text/html; charset=utf-8")
   @ResponseBody
   public String SelectCheckList_Ajax_JSON(
		   HttpServletRequest request, HttpServletResponse response, Model model, HttpSession httpSession, 
		   @ModelAttribute("cheklistItemVO") ChecklistItemVO checklistItemVO,
		   @ModelAttribute("checklistVO") ChecklistVO checklistVO,
		   @ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO
		   )throws Exception
   {
	   String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String task_cd 	= ((String) json.get("TaskPreSet")).trim();
		String ctlg_cd 	= ((String) json.get("TaskSmall")).trim();
		String task		= (String)json.get("task");
		String CltgList = (String)json.get("CltgList");
		String reset	= (String)json.get("reset");
		
		sptlyCodeVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		
		sptlyCodeVO.setTask_cd(task_cd);
		sptlyCodeVO.setCtlg_cd(ctlg_cd);
		sptlyCodeVO.setTask(task);

		List<SptlyCodeVO> searchNew = sptlyCodeService.getActivityCtlgList(sptlyCodeVO);
		if(searchNew.size() > 0)
		{
			sptlyCodeVO.setSeq(searchNew.get(0).getSeq());
		}
		
	    sptlyCodeService.Checklistreset(sptlyCodeVO);
	    
		searchNew = sptlyCodeService.getActivityCtlgList(sptlyCodeVO);
		if(searchNew.size() > 0)
		{
			sptlyCodeVO.setSeq(searchNew.get(0).getSeq());
		}
	    if(reset.equals("N"))
	    {
	    	String code = CltgList.replaceFirst("/","");
	 	   	String[] code2 = null;
	 	   	String[] code3 = null;
	 	   	code3 = new String[10];
	 	   	code2 = code.split("/");

	 	   for(int i = 0;i<code2.length;i++)
	 	   {
	 		   code3[i] = code2[i];
	 		  System.out.println(code3[i]);
	 	   }
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
	    }
	   
	   return "";
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
		   
//		   List<ChecklistItemVO> Checklist = checklistItemService.ChecklistSelect(checklistItemVO);
		   
//		   model.addAttribute("checklistItemVO", checklistItemVO);
//		   model.addAttribute("Checklist", Checklist);
//		   
	   return "./main/ManageCheckListItem";
   }
   
   @SuppressWarnings("unchecked")
@RequestMapping(value="ManageCheckListItem_AJAX_JSON", produces="text/html; charset=utf8")
   @ResponseBody
   public String ManageCheckListItem_AJAX_JSON(
		   @ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
		   HttpServletRequest request,
		   HttpServletResponse response,
		   HttpSession session,
		   ModelMap model
		   )
   throws Exception
   {
	   String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String name = (String) json.get("name");
		String lrgcls = (String) json.get("lrgcls");
		String mdcls = (String) json.get("mdcls");
		
		if(name.equals("All"))
		{
			name = "";
		}
		
		checklistItemVO.setCtlg_cd(name);
		checklistItemVO.setCtlg_itm_cd(lrgcls);
		checklistItemVO.setCtlg_itm_ctnt(mdcls);
		
		List<ChecklistItemVO> Checklist = checklistItemService.ChecklistSelect(checklistItemVO);
		
		int length = Checklist.size();
		JSONArray jaTask = new JSONArray();
		if(length > 0)
		{
			for(int i=0; i<length; i++)
			{
				JSONObject joTask = new JSONObject();
				joTask.put("ctlg_cd"		, Checklist.get(i).getCtlg_cd().trim());
				joTask.put("ctlg_itm_cd"	, Checklist.get(i).getCtlg_itm_cd().trim());
				joTask.put("ctlg_itm_ctnt"	, Checklist.get(i).getCtlg_itm_ctnt());
				joTask.put("stdd_yn"		, Checklist.get(i).getstdd_yn());
				joTask.put("prtcuse_frqc"	, Checklist.get(i).getPrtcuse_frqc());
				
				jaTask.add(joTask);
			}
		}
		else
		{
			JSONObject joTask = new JSONObject();
			
			joTask.put("ctlg_cd"		, "E001");
			joTask.put("ctlg_itm_cd"	, "데이터가 없습니다.");

			jaTask.add(joTask);
		}
		
		String jsonResult = jaTask.toString();
		
		System.out.println(jsonResult);
	   return jsonResult;
   }
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
	       int CodeNew =Integer.parseInt(CodeNum.trim()) + 1;
	       CodeNum = String.format("%03d%n", CodeNew);
	       System.out.println("Code = " + CodeNum);
	       String CodeReal  = Code + "_"  + CodeNum;
	 
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
		 String ctlg_cd = request.getParameter("ctlg_cd");///
		 model.addAttribute("ctlg_cd", ctlg_cd);
		 checklistItemVO = checklistItemService.getChecklistCode(checklistItemVO);
		 model.addAttribute("checklistItemVO", checklistItemVO);
		 
		 // 20190508 add
		 List<CodeVO> largeList = codeService.getCodeListByDvs("L8");
		 model.addAttribute("largeList", largeList);
		 
		 return "./main/ManageCheckListResult";
   }
   //체크리스트항목 일괄 입력
   @RequestMapping("ManageCheckListItem_Result.do")
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
				ctlg_cd = URLEncoder.encode(ctlg_cd, "UTF-8");
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
	    public String ChecklistexcelUploadAjax(
	    		@ModelAttribute("checklistitemVO") ChecklistItemVO checklistitemVO,
	    		MultipartHttpServletRequest request, HttpServletResponse response,
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
	        excelReadOption.setStartRow(2);        
	        
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
	            
	            checklistitemVO.setCtlg_itm_cd(article.get("B"));
	            List<ChecklistItemVO> alreadyList = checklistItemService.ChecklistPreSelect(checklistitemVO);
	            int lengthalready = alreadyList.size();
	            if(lengthalready > 0)
	            {
	            	scriptWriter.getInstance().printScript(response, "<script>alert('중복된 체크리스트가 있습니다. 파일을 확인해주십시오.'); location.href = 'ManageCheckListItem.do';</script>", true);
	            }
	            
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
				
		requestChecklistVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());

		String new_yn = request.getParameter("new_select");
		String state_cd = request.getParameter("state_cd");
		String ctlg_cd = request.getParameter("ctlg_cd");
		String ctlg_itm_cd = request.getParameter("ctlg_itm_cd");
		String rqst_ctnt = request.getParameter("rqst_ctnt");
		String rqst_date = request.getParameter("rqst_date");
		String rqstr_srvno = request.getParameter("rqstr_srvno");
		
	  requestChecklistVO.setNew_yn(new_yn);
	  requestChecklistVO.setState_cd(state_cd);
	  requestChecklistVO.setCtlg_cd(ctlg_cd);
	  requestChecklistVO.setCtlg_itm_cd_1(ctlg_itm_cd);
	  requestChecklistVO.setRqst_ctnt(rqst_ctnt);
	  requestChecklistVO.setRqst_date(rqst_date);
	  requestChecklistVO.setRqstr_srvno(rqstr_srvno);
		
	    
	    List<RequestChecklistVO> requestList;
	    
	    requestList = requestChecklistService.searchRequest(requestChecklistVO);
	    
		List<RequestChecklistVO> CheckReuqest = requestChecklistService.searchRequest(requestChecklistVO);
		
		System.out.println("list : " + CheckReuqest.size());
		
		// paging
		int pageNo = 1; // default
		
		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);
		
		if(pageNoStr != null && !(pageNoStr.equals("")))
			pageNo = Integer.parseInt(pageNoStr);

		Pagination pagination = new Pagination();
		pagination.setPaginationInfo(CheckReuqest, pageNo);
		
		System.out.println("page : " + pagination.getListPerPage(CheckReuqest).size());
		
		Calendar oCalendar = Calendar.getInstance( );
		
		String Date ="";
		
		int Year = oCalendar.get(Calendar.YEAR);
		int Month = oCalendar.get(Calendar.MONTH) + 1;
		int Day = oCalendar.get(Calendar.DAY_OF_MONTH); 
		Date = Year + "-" +Month+"-"+Day;
		   
		SimpleDateFormat realDate = new SimpleDateFormat("yyyy-MM-dd");
		
		Date  realDate1 = realDate.parse(Date);
		
		if(rqst_date != null)
		{
			Date =rqst_date;
		}
		else
		{
			Date = realDate.format(realDate1);

		}

		model.addAttribute("Date", Date);
		model.addAttribute("requestChecklistVO", requestChecklistVO);
		model.addAttribute("CheckReuqest", pagination.getListPerPage(CheckReuqest));
		model.addAttribute("paginationInfo", pagination.getPaginationInfo());
		
//		model.addAttribute("CheckReuqest", CheckReuqest);
		
	
	   return "./main/CheckRequestedCheckList";
   }   
   
   @SuppressWarnings("unchecked")
@RequestMapping(value="CheckRequestCheckList_Search_Ajax_Json.do", produces="text/html; charset=utf-8;")
   @ResponseBody
   	public String CheckRequestCheckList_Search_Ajax_Json(
   			@ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO,
            @ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession httpSession,
            ModelMap model
   			)throws Exception
   	{
	   
		String jsonString = request.getParameter("jsonString");		 //request를 사용하여 jsonString 저장
		   
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String new_yn 		= (String) json.get("new_yn");
		String state_cd 	= (String) json.get("state_cd");
		String ctlg_cd 		= ((String) json.get("ctlg_cd")).trim();
		String ctlg_itm_cd 	= (String) json.get("ctlg_itm_cd");
		String rqst_ctnt 	= (String) json.get("rqst_ctnt");
		String rqst_date 	= (String) json.get("rqst_date");
		String rqstr_srvno 	= (String) json.get("rqstr_srvno");
		
		  if(new_yn .equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setNew_yn(new_yn);
		  }
		  if(state_cd.equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setState_cd(state_cd);
		  }
		  if(ctlg_cd.equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setCtlg_cd(ctlg_cd);
		  }
		  if(ctlg_itm_cd.equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setCtlg_itm_cd_1(ctlg_itm_cd);
		  }
		  if(rqst_ctnt.equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setRqst_ctnt(rqst_ctnt);
		  }
		  if(rqst_date.equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setRqst_date(rqst_date);
		  }
		  if(rqstr_srvno.equals(""))
		  {
			  
		  }
		  else
		  {
			  requestChecklistVO.setRqstr_srvno(rqstr_srvno);
		  }
		
	    requestChecklistVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
	    
	    List<RequestChecklistVO> requestList;
	    
	    requestList = requestChecklistService.searchRequest(requestChecklistVO);
	    
	    int length = requestList.size();
	    JSONArray jaTask = new JSONArray();
	    if(length > 0)
	    {
	    	for(int i=0; i<length; i++){
	    		JSONObject joTask = new JSONObject();
	    		
	    		joTask.put("New_yn",requestList.get(i).getNew_yn());
	    		joTask.put("state_cd", requestList.get(i).getState_cd());
	    		joTask.put("ctlg_cd", requestList.get(i).getCtlg_cd());
	    		if(requestList.get(i).getCtlg_itm_cd_1().equals("-1"))
	    		{
	    			joTask.put("ctlg_itm_cd"," ");
	    		}
	    		else
	    		{
	    			joTask.put("ctlg_itm_cd",requestList.get(i).getCtlg_itm_cd_1());
	    		}
	    		joTask.put("rqst_ctnt", requestList.get(i).getRqst_ctnt());
	    		joTask.put("rqst_date", requestList.get(i).getRqst_date());
	    		joTask.put("rqstr_srvno", requestList.get(i).getRqstr_srvno());
	    		joTask.put("seq", requestList.get(i).getSeq());
	    		
	    		jaTask.add(joTask);
	    	}
	    }
	    else
	    {
			JSONObject joTask = new JSONObject();
			
			joTask.put("ctlg_cd","E001");
			
			jaTask.add(joTask);
	    }
	   
	    String jsonResult = jaTask.toString();
	   return jsonResult;
   	}
   
   @RequestMapping(value="CheckRequest_Result.do")
   public String CheckRequestResult(
           @ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO,
           @ModelAttribute("checklistItemVO") ChecklistItemVO checklistItemVO,
           HttpServletRequest request,
           HttpSession httpSession,
           ModelMap model
           )throws Exception{
       String Seq;
       String state_cd;
       String rvw_opn;
       String Approved = ("D2");
       String Denied =("D3");
       String  isNew = "";
       String ctlg_cd;
       String ctlg_itm_ctnt;
       String ctlg_itm_cd_1;
       
       Seq = request.getParameter("seq");
       requestChecklistVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd());
       List<RequestChecklistVO> requestList = requestChecklistService.getChecklistRequest(requestChecklistVO);
       state_cd = request.getParameter("state_cd");
       rvw_opn = request.getParameter("rvw_opn");
       ctlg_cd = requestList.get(0).getRqstr_nm();
       ctlg_itm_ctnt = request.getParameter("ctlg_itm_ctnt");
       ctlg_itm_cd_1 = request.getParameter("cltg_itm_cd_1");
       
       requestChecklistVO.setSeq(Seq);
       requestChecklistVO.setState_cd(state_cd);
       requestChecklistVO.setOpn(rvw_opn);
       checklistItemVO.setCtlg_cd(ctlg_cd);
       
       if(ctlg_itm_cd_1 == null)
       {
    	   
       }
       else if(ctlg_itm_cd_1.equals("-1") )
       {
    	   isNew = "-1";
       }
       else
       {
    	   isNew = "";
       }
       if(state_cd.equals(Approved) ){
           if(isNew.equals("") || isNew.equals("-1")){
               List<ChecklistItemVO> CheckCreate = requestChecklistService.getCheckCreate(checklistItemVO);
               
               String lastKeyword;
               
               lastKeyword = CheckCreate.get(CheckCreate.size() - 1).ctlg_itm_cd;
               String[] array = lastKeyword.split("_");
               String Code = array[0];
               String CodeNum = array[1];
               CodeNum = CodeNum.trim();
               int CodeNew =Integer.parseInt(CodeNum);           
               CodeNew = CodeNew + 1;
               String CodeReal  = Code + "_"  + String.valueOf(CodeNew);
               
               checklistItemVO.setCtlg_itm_cd(CodeReal);
               checklistItemVO.setCtlg_itm_ctnt(ctlg_itm_ctnt);
               
               
//               requestChecklistService.RequestCreateNew(checklistItemVO);
               requestChecklistService.RequestDeny(requestChecklistVO);
                 model.addAttribute("returnUrl", "CheckRequestedCheckList.do");
                 model.addAttribute("RST", "CommandModifyOK");
                 return "message";
           }
           else{
        	   requestChecklistVO.setCtlg_itm_cd_2(ctlg_itm_ctnt);
        	   requestChecklistVO.setCtlg_itm_cd_1(ctlg_itm_cd_1);
               requestChecklistService.RequestDeny(requestChecklistVO);   
//               requestChecklistService.RequestAccept(requestChecklistVO);
               
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
	   		requestChecklistVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd());
			List<RequestChecklistVO> CheckReuqest = requestChecklistService.getChecklistRequest(requestChecklistVO);
			requestChecklistVO.setState_cd(CheckReuqest.get(0).state_cd);
			
			model.addAttribute("CheckReuqest", CheckReuqest);
			model.addAttribute("requestChecklistVO", requestChecklistVO);
			
			System.out.println("new : "+ CheckReuqest.get(0).getNew_yn());
		
		   return "./main/CheckRequestedCheckSelect";
	   }   
   
	// seungwon
	// seungwon 19.02.12 //
	@SuppressWarnings("static-access")
	@RequestMapping(value = "loginAction.do")
	public String loginAction(final HttpServletResponse response, HttpServletRequest request, HttpSession httpSession) throws Exception {
		System.out.println("login start");
		System.out.println("client ip : " + new NetworkState().getClientIP(request));
		if(httpSession.getAttribute("loginedUser") != null)	
		{
			httpSession.removeAttribute("loginedUser");
		}		
		System.out.println("login addr : "+request.getRemoteAddr());
		System.out.println("login Host : "+request.getRemoteHost());
		String userIp = request.getRemoteAddr();
		String userHostName = request.getRemoteHost();
		
		boolean doLogin = false;
		
		//String formSrvno = (String)httpSession.getAttribute("SS_SN");			// for service
		//String formPw = (String)httpSession.getAttribute("SS_PW");			// for service
		String formSrvno = request.getParameter("srvno");						// for service : delete this
		String formPw = request.getParameter("pw");								// for service : delete this
		System.out.println("srvno : " + formSrvno);
		UserVO tempUserVO = loginService.getUserVO(formSrvno);
		String rslt = (String) httpSession.getAttribute("SS_RSLT");
		rslt = "true";															// for service : delete this
		if (rslt == null) {
			scriptWriter.getInstance().printScript(response, "<script>alert('현역실명인증값이 올바르지 않습니다. 관리자에게 문의하십시오.'); location.href = 'login.do';</script>", true);
		}
		
		if (rslt.trim().equals("true")) {
			if (tempUserVO != null) 
			{
				System.out.println("vo is not null");
				
				if (tempUserVO.getacc_state_info().trim().equals("K2")) 
				{
					scriptWriter.getInstance().printScript(response, "<script>alert('계정이 잠금상테입니다. 관리자에게 문의하십시오.'); location.href = 'login.do';</script>", true);
				}
				else 
				{				
					if (tempUserVO.getPw().equals(formPw)) {
						
						System.out.println("pw is true");
						
						if(tempUserVO.getathrt().trim().equals("B1")) { // for ip : tempUserVO.getathrt().trim().equals("B1") / for debug : true
							if (loginService.CountChangePwDate(tempUserVO.getSrvno()) >= 30) {
								httpSession.setAttribute("changePwSrv",	tempUserVO.getSrvno());
								scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호를 변경한지 30일이 지났습니다. 비밀번호를 변경해주십시오.'); location.href = 'PasswordChange.do';</script>", true);
							} 
							else {					
								httpSession.setAttribute("SS_SN", tempUserVO.getSrvno()); 					// 군번
								httpSession.setAttribute("SS_ATHRT", tempUserVO.getathrt()); 			// 권한 코드
								httpSession.setAttribute("SS_RNK", tempUserVO.getRnkcd_nm()); 				// 계급명
								httpSession.setAttribute("SS_NM", tempUserVO.getStmt());					// 이름
								httpSession.setAttribute("SS_IDTF", tempUserVO.getIncdt_idtf_cd_nm()); 	// 소속부대
								httpSession.setAttribute("SS_MNT", tempUserVO.getmontr());			// 모니터링 권한
								httpSession.setAttribute("SS_YEAR", DateUtil.getCurrentYear()); 			// year
								httpSession.setAttribute("SS_MON", DateUtil.getCurrentMonth()); 			// month
								httpSession.setAttribute("SS_DAY", DateUtil.getCurrentDay()); 				// day						
								httpSession.setAttribute("loginDate", new Date().getTime()); 				// loginDate
								loginService.InitErrCount(tempUserVO);
								loginService.updateLoginDate(tempUserVO);
								
								// update login user vo
								tempUserVO = loginService.getUserVO(tempUserVO.getSrvno());				
								
								httpSession.setAttribute("loginedUser", tempUserVO);				// 유저 객체

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
									scriptWriter.getInstance().printScript(response, "<script>alert('접속 불가능한 IP입니다. host: ["+userIp+"] ["+userHostName+"] / db: ["+tempUserVO.getIp().trim()+"] 지정된 PC에서 접속하여야합니다.'); location.href = 'login.do';</script>", true);
								}
								else {
									System.out.println("ip is true");
									if (loginService.CountChangePwDate(tempUserVO.getSrvno()) >= 30) {
										httpSession.setAttribute("changePwSrv",	tempUserVO.getSrvno());
										scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호를 변경한지 30일이 지났습니다. 비밀번호를 변경해주십시오.'); location.href = 'PasswordChange.do';</script>", true);
									} 
									else {
										httpSession.setAttribute("SS_SN", tempUserVO.getSrvno()); 					// 군번
										httpSession.setAttribute("SS_ATHRT", tempUserVO.getathrt()); 			// 권한 코드
										httpSession.setAttribute("SS_RNK", tempUserVO.getRnkcd_nm()); 				// 계급명
										httpSession.setAttribute("SS_NM", tempUserVO.getStmt());					// 이름
										httpSession.setAttribute("SS_IDTF", tempUserVO.getIncdt_idtf_cd_nm()); 	// 소속부대
										httpSession.setAttribute("SS_MNT", tempUserVO.getmontr());			// 모니터링 권한
										httpSession.setAttribute("SS_YEAR", DateUtil.getCurrentYear()); 			// year
										httpSession.setAttribute("SS_MON", DateUtil.getCurrentMonth()); 			// month
										httpSession.setAttribute("SS_DAY", DateUtil.getCurrentDay()); 				// day						
										httpSession.setAttribute("loginDate", new Date().getTime()); 				// loginDate
										loginService.InitErrCount(tempUserVO);
										loginService.updateLoginDate(tempUserVO);
										
										// update login user vo
										tempUserVO = loginService.getUserVO(tempUserVO.getSrvno());						
										
										httpSession.setAttribute("loginedUser", tempUserVO);				// 유저 객체

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
							return null;
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
		}			
		return "./main/logout";
	}

	// seungwon 19.02.17~ // 시작버튼, 최종완료버튼 눌렀을때
	@RequestMapping(value = "Task_Start.do")
	public String Task_Start(
			HttpSession httpSession, 
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO)
			throws Exception {
		System.out.println("into task_start.do");

		listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		
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
			HttpSession httpSession, 
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO)
			throws Exception {
		System.out.println("into task_finish.do");

		listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		
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
			HttpSession httpSession, 
			ModelMap mod) throws Exception {
		System.out.println(listsearchVO.getActId());
		System.out.println(listsearchVO.getSrvno());
		listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd()); // 사용자의 부대코드
		
		TaskDataVO taskData = null;
		TaskDataVO forSearch = null;
		
		if (listsearchVO.getAfter_rqst_data_seq() != null && !(listsearchVO.getAfter_rqst_data_seq().trim().equals(""))) {
			taskData = taskDataService.getTaskDataBySeq(listsearchVO.getAfter_rqst_data_seq());
		}
		else {
			// temp data for search
			forSearch = new TaskDataVO();
			forSearch.setId(listsearchVO.getActId());
			forSearch.setTask_psnchnrg_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
			forSearch.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd());
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
			HttpSession httpSession, 
			RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("complete Start..");

		ChecklistItemVO tempItemVO = checklistItemService
				.getChecklistItemVO(listsearchVO);

		ActivityVO tempActVO = activityService.getActivityVO(tempItemVO, ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());

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
			HttpSession httpSession, 
			ModelMap mod) throws Exception {
		System.out.println("monitor0..");
		
		String pageNoStr = (String) request.getParameter("currentPageNo");
		System.out.println("pageNoStr : " + pageNoStr);
		
		if (!(((UserVO)httpSession.getAttribute("loginedUser")).getathrt().trim().equals("B4")))
			listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		
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
			HttpSession httpSession, 
			ModelMap mod) throws Exception {

		if(listsearchVO.getIncdt_idtf_cd() == null || listsearchVO.getIncdt_idtf_cd() == "") {
			listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
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
			HttpSession httpSession) throws Exception {
		
		if (httpSession.getAttribute("loginedUser") == null) {
			return "redirect:/login.do";
		}

		// get code list for select activity
		List<CodeVO> largeList = codeService.getCodeListByDvs("L8");
		List<CodeVO> middleList = codeService.getCodeListByDvs("L9");
		List<CtlgCodeVO> ctlgList = ctlgCodeService.getAllCtlgCodeList();
		
		model.addAttribute("largeList", largeList);
		model.addAttribute("middleList", middleList);
		model.addAttribute("ctlgList", ctlgList);

		return "./main/OccasionalSafeManagement_Request";
	}

	// add safe request
	@RequestMapping(value = "Add_SafeRequest.do")
	public String Add_SafeRequest(
			HttpSession httpSession, 
			@ModelAttribute("requestActivityVO") RequestActivityVO requestActivityVO)
			throws Exception {
		// add safe request to database

		// 요청자 군번, 기본 상태 코드 ( 승인대기 )
		requestActivityVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		requestActivityVO.setRqstr_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
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
		
		System.out.println("seq : " + listsearchVO.getSeq());
		
		TaskDataVO taskData = taskDataService.getTaskDataBySeq(listsearchVO.getSeq());
		
		model.addAttribute("cmdNum", listsearchVO.getCmdNum());
		model.addAttribute("taskData", taskData);
		
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
			HttpSession httpSession, 
			RedirectAttributes redirectAttributes, HttpServletResponse response)
			throws Exception {
		
		CheckApprovalVO tempAppVO = checkApprovalService.getApprovalByVO(checkApprovalVO);
		
		if(tempAppVO != null) {
			scriptWriter.getInstance().printScript(response, "<script>alert('이미 요청되어있습니다.'); location.href = history.back();</script>", true);
			return null;
		}
		else {
			System.out.println("start");
			System.out.println("task seq : " + checkApprovalVO.getTaskDataSeq());
			TaskDataVO taskData = taskDataService.getTaskDataBySeq(checkApprovalVO.getTaskDataSeq());
			System.out.println("task data vo : " + taskData.getId());
			System.out.println(taskData.getState_cd());
			
			System.out.println("rsn : " + checkApprovalVO.getRsn());

			// insert approval into database
			checkApprovalVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim()); // incdt_idtf_cd
			checkApprovalVO.setRqstr_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno()); // srvno
			
			// get act code
			ActivityTypeVO actType = activityTypeService.getActTypeByCtlg(taskData.getCtlg_cd());
			System.out.println("act type vo : " + actType);
			
			checkApprovalVO.setIncdt_actvt_type_cd(actType.getIncdt_actvt_type_cd());
			checkApprovalVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim()); // incdt_idtf_cd
			checkApprovalVO.setActvt_date(taskData.getActvt_date());	// act date
			checkApprovalVO.setState_cd("D1");	// state_cd = default : D1, D2, D3
			checkApprovalVO.setId(taskData.getId());
			
			System.out.println(checkApprovalVO.getActvt_date());
			
			//checkApprovalService.insertApproval(checkApprovalVO);
			
			System.out.println("insert complete");
			System.out.println("task itm 1 : " + taskData.getCtlg_itm_cd_1().trim());
			System.out.println("chk itm 1 : " + checkApprovalVO.getCtlg_itm_cd().trim());
			
			// change item state code in activity AND update
			boolean isChecked = false;
			
			if (taskData.getCtlg_itm_cd_1() != null) {
				if(taskData.getCtlg_itm_cd_1().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_1("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_2() != null) {
				if(taskData.getCtlg_itm_cd_2().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_2("C");
					isChecked = true;
				}
			}
			if (taskData.getCtlg_itm_cd_3() != null) {
				if(taskData.getCtlg_itm_cd_3().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_3("C");
					isChecked = true;
				}
			}
			if (taskData.getCtlg_itm_cd_4() != null) {
				if(taskData.getCtlg_itm_cd_4().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_4("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_5() != null) {
				if(taskData.getCtlg_itm_cd_5().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_5("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_6() != null) {
				if(taskData.getCtlg_itm_cd_6().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_6("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_7() != null) {
				if(taskData.getCtlg_itm_cd_7().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_7("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_8() != null) {
				if(taskData.getCtlg_itm_cd_8().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_8("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_9() != null) {
				if(taskData.getCtlg_itm_cd_9().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_9("C");
					isChecked = true;
				}					
			}
			if (taskData.getCtlg_itm_cd_10() != null) {
				if(taskData.getCtlg_itm_cd_10().trim().equals(checkApprovalVO.getCtlg_itm_cd().trim())) {
					taskData.setCtlg_itm_yn_10("C");
					isChecked = true;
				}					
			}			
			
			System.out.println("chk itm 1 : " + checkApprovalVO.getCtlg_itm_cd().trim());
			System.out.println("task itm 1 : " + taskData.getCtlg_itm_cd_10());
			System.out.println("is checked : " + isChecked);
			if (isChecked == true) {
				checkApprovalService.insertApproval(checkApprovalVO);
				System.out.println("insert approval");
				taskDataService.updateYNState(taskData);
				System.out.println("insert taskdata");
			}
			else {
				scriptWriter.getInstance().printScript(response, "<script>alert('조치요청을 실패하였습니다. 다시 시도해주십시오.'); location.href = history.back();</script>", true);
				return null;
			}
			
			
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
		List<ChecklistItemVO> tempList = checklistItemService.getAllChecklistItemList();		
		model.addAttribute("checkList", tempList);
		
		// get largeList
		List<CodeVO> largeList = codeService.getCodeListByDvs("L8");
		model.addAttribute("largeList", largeList);
		
		return "./main/CheckListManagement_Request";
	}
	
	// 체크리스트 추가/수정 요청 처리 함수
	@RequestMapping(value = "Add_RequestChecklist.do")
	public String Add_RequestChecklist(
			HttpSession httpSession, 
			@ModelAttribute("requestChecklistVO") RequestChecklistVO requestChecklistVO) throws Exception {
		// 요청 체크리스트에 요청자 군번, 상태코드 부여
		requestChecklistVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		requestChecklistVO.setRqstr_srvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		requestChecklistVO.setState_cd("D1");
		
		requestChecklistService.insertRequestChecklist(requestChecklistVO);
		
		return "redirect:/CheckListManagement.do";
	}
	
	// 지휘관지침 완료
	@RequestMapping(value = "CmdList_Complete.do")
	public String CmdList_Complete(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpSession httpSession, 
			RedirectAttributes redirectAttributes) throws Exception {
		
		listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		listsearchVO.setSrvno(((UserVO)httpSession.getAttribute("loginedUser")).getSrvno());
		
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
					if (httpSession.getAttribute("changePwSrv") != null)
						httpSession.removeAttribute("changePwSrv");
					scriptWriter.getInstance().printScript(response, "<script>alert('비밀번호가 변경되었습니다.'); location.href = 'login.do';</script>", true);
					return null;
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
				System.out.println("Large : " + Large);

				String jsonString = request.getParameter("jsonString");		 //request를 사용하여 jsonString 저장
				   
				jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
				   
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
					   
						   
				membersearchVO.setSearchConditionLarge(Large);
				taskVO.setLrgcls(Large);
				
				List<TaskVO> taskLarge;
				List<TaskVO> taskMiddle;
				List<TaskVO> taskSmall;
				
				if (Large.equals(""))
				{
					membersearchVO.setEnd(1);
					taskLarge = taskService.getTaskLarge(membersearchVO);
					taskMiddle = taskService.getTaskMiddle(membersearchVO);
					taskSmall 	= taskService.getTaskSearchOption(taskVO);
				}
				else
				{
				taskLarge 	= taskService.getTaskLarge(membersearchVO);
				taskMiddle 	= taskService.getTaskMiddle(membersearchVO);
				taskSmall 	= taskService.getTaskSearchOption(taskVO); 
				}
				
				int largelenth = taskLarge.size();
				int length = taskMiddle.size();
				int Smalllength = taskSmall.size();
				
					String newResult = "";
					JSONArray jaLarge = new JSONArray();
					JSONArray jaMiddle = new JSONArray();
					JSONArray jaSmall = new JSONArray();

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
					for(int i=0; i<Smalllength; i++){
						JSONObject joSmall = new JSONObject();
						String ctlg_cd = taskSmall.get(i).getCtlg_cd();
						String ctlg_nm = taskSmall.get(i).getCtlg_nm();
						
						joSmall.put("ctlg_cd", ctlg_cd);
						joSmall.put("ctlg_nm", ctlg_nm);
						
						jaSmall.add(joSmall);
					}
					
				JSONObject resultjson = new JSONObject();
				resultjson.put("Middle"	, jaMiddle);
				resultjson.put("Small", jaSmall);
				
				
				String jsonResult = resultjson.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
				
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
		
				//IE encoding error fixed
				request.setCharacterEncoding("UTF-8");
				String Middle = URLDecoder.decode((request.getParameter("Middle")),"UTF-8");
				
				//System.out.println("Middle : "+Middle);
			
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
		
		String jsonString = request.getParameter("jsonString");
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
			   
		String OptionLarge = (String) json.get("OptionLarge");
			OptionLarge = OptionLarge.trim();
		
		String OptionMiddle = (String) json.get("OptionMiddle");
			OptionMiddle = OptionMiddle.trim();
		
		String TaskNameSearch = (String) json.get("TaskNameSearch");	//Task cd인지 nm인지 체크
		String TaskCodeSearch = (String) json.get("TaskCodeSearch");
		
		List<TaskVO> TaskSearchList = null;
		
		if(TaskNameSearch == null ||TaskNameSearch.equals(""))
		{
			if( TaskCodeSearch == null || TaskCodeSearch.equals(""))
			{
				if(OptionLarge != null)
				{
					taskVO.setLrgcls(OptionLarge);
					TaskSearchList = taskService.getTaskSearchOption(taskVO);
				}
			}
		}
		
		if(TaskNameSearch!=null)
		{
			taskVO.setCtlg_nm(TaskNameSearch);
			if(
					OptionLarge.equals("")|| OptionMiddle.equals("")
					)
			{
				TaskSearchList = taskService.getTaskSearchName(taskVO);	
			}
			else
			{
				taskVO.setLrgcls(OptionLarge);
				taskVO.setMdcls(OptionMiddle);
				
				TaskSearchList = taskService.getTaskSearchNameWithOption(taskVO);
			}
		}
		else
		{
			taskVO.setCtlg_cd(TaskCodeSearch);
			if(
					OptionLarge.equals("")|| OptionMiddle.equals("")
					)
			{
				TaskSearchList = taskService.getTaskSearchName(taskVO);	
			}
			else
			{
				taskVO.setLrgcls(OptionLarge);
				taskVO.setMdcls(OptionMiddle);
				
				TaskSearchList = taskService.getTaskSearchNameWithOption(taskVO);
			}
		}
		
		int length = TaskSearchList.size();
		
		JSONArray jaTask = new JSONArray();
		if(length > 0)
		{
			for(int i=0;i<length;i++){
				JSONObject joTask = new JSONObject();
				joTask.put("ctlg_cd", TaskSearchList.get(i).ctlg_cd);
				joTask.put("ctlg_nm", TaskSearchList.get(i).ctlg_nm);
				joTask.put("lrgcls", TaskSearchList.get(i).lrgcls.trim());
				joTask.put("lrgcls_nm", TaskSearchList.get(i).lrgcls_nm);
				joTask.put("mdcls", TaskSearchList.get(i).mdcls.trim());
				joTask.put("mdcls_nm", TaskSearchList.get(i).mdcls_nm);
				
				jaTask.add(joTask);
			}
		}
		else
		{
			JSONObject joTask = new JSONObject();
			joTask.put("ctlg_cd", "E001_001");
			joTask.put("ctlg_nm", "데이터가 없습니다.");
			joTask.put("lrgcls", OptionLarge);
			joTask.put("lrgcls_nm", "etc");
			joTask.put("mdcls", OptionMiddle);
			joTask.put("mdcls_nm", "etc");
			
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
		
		String OptionLarge = (String) json.get("OptionLarge");
		String OptionMiddle = (String) json.get("OptionMiddle");
		OptionLarge = OptionLarge.trim();
		OptionMiddle = OptionMiddle.trim();
		List<TaskVO> TaskSearchList;
			   
		taskVO.setCtlg_cd(TaskNameSearch);
		
		if(
				OptionLarge.equals("")|| OptionMiddle.equals("")
				)
		{
			TaskSearchList = taskService.getTaskSearchName(taskVO);	
		}
		else
		{
			taskVO.setLrgcls(OptionLarge);
			taskVO.setMdcls(OptionMiddle);
			
			TaskSearchList = taskService.getTaskSearchNameWithOption(taskVO);
		}
		
		int length = TaskSearchList.size();
		
		JSONArray jaTask = new JSONArray();
		
		if(length > 0)
		{
			for(int i=0;i<length;i++){
				JSONObject joTask = new JSONObject();
				joTask.put("ctlg_cd", TaskSearchList.get(i).ctlg_cd);
				joTask.put("ctlg_nm", TaskSearchList.get(i).ctlg_nm);
				joTask.put("lrgcls", TaskSearchList.get(i).lrgcls.trim());
				joTask.put("lrgcls_nm", TaskSearchList.get(i).lrgcls_nm);
				joTask.put("mdcls", TaskSearchList.get(i).mdcls.trim());
				joTask.put("mdcls_nm", TaskSearchList.get(i).mdcls_nm);
				
				jaTask.add(joTask);
			}
		}
		else
		{
			JSONObject joTask = new JSONObject();
			joTask.put("ctlg_cd", "E001_001");
			joTask.put("ctlg_nm", "데이터가 없습니다.");
			joTask.put("lrgcls", OptionLarge);
			joTask.put("lrgcls_nm", "etc");
			joTask.put("mdcls", OptionMiddle);
			joTask.put("mdcls_nm", "etc");
			
			jaTask.add(joTask);
			
		}
		
		String jsonResult = jaTask.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println(jsonResult);

		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="AssignTask_TaskPre_Ajax.do", produces="text/html; charset=utf-8;")
	@ResponseBody
	public String AssignTask_TaskPre_Ajax(
			@ModelAttribute("sptlyCodeVO") SptlyCodeVO sptlyCodeVO,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession,
			ModelMap model
			)throws Exception
	{
		String Taskname = request.getParameter("Name");
		String jsonString = request.getParameter("jsonString");
		String incdt = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim();
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		String Ctlg_cd = (String) json.get("Name");
		
		Ctlg_cd = Ctlg_cd.trim();
		
		sptlyCodeVO.setCtlg_cd(Ctlg_cd);
		sptlyCodeVO.setIncdt_idtf_cd(incdt);
		
		List<SptlyCodeVO> PreList;
		
		PreList = sptlyCodeService.getTaskDataList(sptlyCodeVO);
		JSONArray jaTask = new JSONArray();
		int length = PreList.size();
		if (length > 0)
		{
			for(int i=0; i<length; i++){
				JSONObject joTask = new JSONObject();
				
				joTask.put("task", PreList.get(i).getTask());
				joTask.put("task_cd",PreList.get(i).getTask_cd().trim());
				
				jaTask.add(joTask);
			}
		}
		
		
		String jsonResult = jaTask.toString();
		return jsonResult;
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
	@RequestMapping(value="Modal_Cls_Search.do", produces="text/html;charset=utf-8")
	@ResponseBody
	public String Modal_Cls_Search(
			@ModelAttribute("taskVO") TaskVO taskVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model
			) throws Exception{
		String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String Ctlg_cd = (String) json.get("Code");
		Ctlg_cd = Ctlg_cd.trim();
		List<TaskVO> TaskClsList;
		List<TaskVO> taskMiddle;
		
		if(Ctlg_cd.equals(""))
		{
		}
		else{
		taskVO.setCtlg_cd(Ctlg_cd);
		}
		TaskClsList = taskService.getClsList(taskVO);
		int length = TaskClsList.size();
		if(length > 0)
		{
		membersearchVO.setSearchConditionLarge(TaskClsList.get(0).getLrgcls());
		}
		taskMiddle = taskService.getTaskMiddle(membersearchVO);
		int lengthMiddle = taskMiddle.size();
		
		JSONArray jaTask = new JSONArray();
		JSONArray jaTask1 = new JSONArray();
		JSONObject JoResult = new JSONObject();
		for(int i=0;i<length;i++){
			JSONObject joTask = new JSONObject();
			
			joTask.put("lrgcls", TaskClsList.get(i).lrgcls);
			joTask.put("lrgcls_nm", TaskClsList.get(i).lrgcls_nm);
			joTask.put("mdcls_select", TaskClsList.get(i).mdcls);
			joTask.put("mdcls_select_nm", TaskClsList.get(i).mdcls_nm);
			
			jaTask.add(joTask);
		}
		JoResult.put("lrg", jaTask);
		for(int i=0;i<lengthMiddle;i++){
			JSONObject joTask = new JSONObject();
			
			joTask.put("mdcls", taskMiddle.get(i).cd);
			joTask.put("mdcls_nm", taskMiddle.get(i).cd_nm);
			
			jaTask1.add(joTask);
		}
		JoResult.put("md", jaTask1);
		String jsonResult = JoResult.toString();
		System.out.println(jsonResult);
		
		return jsonResult;
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
		membersearchVO.setSearchCode(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
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
			HttpSession httpSession, 
			Model model)
	throws Exception
	{
		String jsonString = request.getParameter("jsonString");
		jsonString = jsonString.replaceAll("&quot;", "\"");
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject)parser.parse(jsonString);
		
		String code = (String) json.get("Name");
		
		taskDataVO.setTask_cd(code);
		taskDataVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		sptlyCodeVO.setCtlg_cd(code);
		sptlyCodeVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
				
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
			ModelMap model, String flag, HttpServletRequest request,
			HttpSession httpSession
			)throws Exception{

		
		if(((UserVO)httpSession.getAttribute("loginedUser")).getathrt().trim().equals("B2"))
		{
			troopsVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd().trim());
		}
		List<TroopsVO> TroopsList = troopsService.getTroopsList(troopsVO);
		model.addAttribute("TroopsList", TroopsList);
		return  "/main/Troops_Modal";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "TroopsSearchAjax.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String TroopsSearchAjax(
			@ModelAttribute("troopsVO") TroopsVO troopsVO,
			@ModelAttribute("MembersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession,
			ModelMap model
			)throws Exception
	{
		String jsonString = request.getParameter("jsonString");
		
		jsonString = jsonString.replaceAll("&quot;", "\""); 
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		String Name = (String)json.get("Name");
		
		troopsVO.setIncdt_nm(Name);
		
		List<TroopsVO> TroopsList = troopsService.getTroopsList(troopsVO);
		JSONArray jaTask = new JSONArray();
		
		int length = TroopsList.size();
		if(length > 0)
		{
			for(int i=0;i<length;i++){
				JSONObject JoTask = new JSONObject();
				JoTask.put("incdt_idtf_cd", TroopsList.get(i).getIncdt_idtf_cd());
				JoTask.put("incdt_nm", TroopsList.get(i).getIncdt_nm());
				
				jaTask.add(JoTask);
			}
		}
		else
		{
			JSONObject joTask = new JSONObject();
			joTask.put("incdt_idtf_cd", "E001");
			jaTask.add(joTask);
		}
		String jsonResult = jaTask.toString();
		return jsonResult;
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
		String flag = "false";
		
		flushStr += "<script>";		
		
		if (result == null || !result.equals("0")) {
			if (result.equals("1")) {
				flushStr += "alert('검증체계에 등록되지 않은 군번입니다. 관리자에게 문의하십시오');";
				returnUrl = "redirect:/index.do";
			}				
			else if (result.equals("2")) {
				flushStr += "alert('당일 해당군번에 대한 인증 5회 실패로 오늘은 검증 불가능합니다. \n\n 24시간 이후 다시 시도하시길 바랍니다.');";
				returnUrl = "redirect:/index.do";
			}				
			else if (result.equals("3")) {
				flushStr += "alert('현역인증실명 키가 일치하지 않습니다. \n\n인트라넷 '육군포털>모바일육군'에서 확인 후 입력바랍니다.'); history.back();";
				returnUrl = "";
			}				
		}	
		else {
			flag = "true";
			returnUrl = "redirect:/loginAction.do";
		}
		
		httpSession.setAttribute("SS_RSLT", flag);		
	
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
            	return null;
            }
            
            CodeVO codeVO = new CodeVO();
            
            codeVO.setCd(article.get("A"));
            codeVO.setCd_nm(article.get("B"));
            codeVO.setRmrk(article.get("C"));
            codeVO.setGrp_cd(article.get("D"));
            
            tempList.add(codeVO);
        }
        
        // throw exception
        if (tempList == null) {
        	scriptWriter.getInstance().printScript(response, "<script>alert('파일에 정보가 없습니다. 파일을 확인해주십시오.'); location.href = 'Code_Insert_Excel.do';</script>", true);
        	return null;
        }
        
        // search dupl code
        int result = 0;
        String duplicateCode = "";
        
       	for(int i = 0; i < tempList.size(); ++i) {
           	if(codeService.searchDuplicateCode(tempList.get(i)) != null) {
           		result = -1;
           		duplicateCode = tempList.get(i).getCd();
           		break;
           	}
       	}        
        
        // insert
        if (result == -1) {
        	scriptWriter.getInstance().printScript(response, "<script>alert('이미 동일한 코드가 존재합니다. ["+ duplicateCode +"] 파일을 확인해주십시오.'); location.href = 'Code_Insert_Excel.do';</script>", true);
        	return null;
        }
        else {
        	codeService.insertCodeListInExcel(tempList);
        }        
        
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
		String currentSelectedCondition = request.getParameter("currentSelectedCondition");
		
		System.out.println("selected : " + currentSelected);
		
		// set value for search
		if(codeSearchVO.getSearch_dvs() == null || codeSearchVO.getSearch_dvs().equals(""))
			codeSearchVO.setSearch_dvs(currentSelected);
		if(codeSearchVO.getSearch_nm() == null || codeSearchVO.getSearch_nm().equals(""))
			codeSearchVO.setSearch_nm(currentSearch);
		codeSearchVO.setSearch_condition(currentSelectedCondition);
		
		int pageNo = 1;
		
		if(pageNoStr != null && !pageNoStr.equals(""))
			pageNo = Integer.parseInt(pageNoStr);

		System.out.println("pageNo : " + pageNo);
		
		List<CodeVO> codeList = null;
		
		// 총 객체 갯수를 page 클래스에 입력
		if((codeSearchVO.getSearch_dvs() == null || codeSearchVO.getSearch_dvs().equals("")) 
				&& (codeSearchVO.getSearch_nm() == null || codeSearchVO.getSearch_nm().equals(""))) {
			codeList = codeService.getAllCodeList(); 
			System.out.println("MainController.java > ManageCode.do > allCode");
		}
		else {
			System.out.println("MainController.java > ManageCode.do > someCode");
			codeList = codeService.getCodeList(codeSearchVO);
		}
		
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
		
		// get code list for select activity
		List<CodeVO> largeList = codeService.getCodeListByDvs("L8");
		
		// get cls list
		List<ClsVO> clsList = clsService.getAllClsList();

		model.addAttribute("groupList", viewGroupList);		
		model.addAttribute("largeList", largeList);
		model.addAttribute("clsList", clsList);
		
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
	public String Code_Insert_Action(@ModelAttribute("codeVO") CodeVO codeVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		// search duplicate code
		CodeVO duplVO = codeService.searchDuplicateCode(codeVO);
		System.out.println("dupl : " + duplVO);
		// insert
		if (duplVO == null) {			
			codeService.insertCodeVO(codeVO);
			
			// if (insert middle cls code) > insert cls table
			if (codeVO.getGrp_cd().equals("L9")) {
				String large = (String) request.getParameter("large");
				
				ClsVO clsVO = new ClsVO();
				clsVO.setLrgcls(large);				// large
				clsVO.setMdcls(codeVO.getCd());		// middle
				
				clsService.insertClsVO(clsVO);
			}
			else if (codeVO.getGrp_cd().equals("L7")) {
				String large = (String) request.getParameter("large");
				String middle = (String) request.getParameter("middle");
				
				CtlgCodeVO ctlgVO = new CtlgCodeVO();
				ctlgVO.setCtlg_cd(codeVO.getCd());
				ctlgVO.setCtlg_nm(codeVO.getCd_nm());
				ctlgVO.setLrgcls(large);
				ctlgVO.setMdcls(middle);
				
				ctlgCodeService.insertCtlgCodeVO(ctlgVO);
			}
		}
		else {
			scriptWriter.getInstance().printScript(response, "<script>alert('이미 등록되어있는 코드입니다.'); history.back();</script>", true);
			return null;
		}
		
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
			groupCodeList = groupCodeService.getAllGroupCodeListBySort();
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
	
	@RequestMapping(value = "EnterCommanderGuide_InsertAll.do")
	public String EnterCommanderGuide_InsertAll(HttpServletRequest request, ModelMap model) throws Exception {
		
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
	
	@RequestMapping(value = "EnterComGuide.do")
	public String EnterComGuide(@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, HttpServletRequest request, ModelMap model) throws Exception {
		System.out.println("into enter com guide");		
		System.out.println("name : " + listsearchVO.getSearch_by_name());
		System.out.println("Date : " + listsearchVO.getDate());
		
		if (request.getParameter("Name") != null) {
			listsearchVO.setSearch_by_name((String) request.getParameter("Name"));
		}
		
		if (listsearchVO.getDate() == null || listsearchVO.getDate().equals("")) {
			String date = new DateUtil().getTodayDate(false);
			System.out.println("date : " + date);
			listsearchVO.setDate(date);
		}
		
		return "./main/EnterCommanderGuide";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "EnterComGuide_Search.do")
	public String EnterComGuide_Search(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO,
			HttpSession httpSession, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		System.out.println("list Date : " + listsearchVO.getDate());
		
		// get attribute
		String Name = (String) request.getParameter("Name");
		String Code = (String) request.getParameter("Code");
		String Date = (String) request.getParameter("Date");
		String idtf = ((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd();	
		
		System.out.println("Name : " + Name);
		System.out.println("Code : " + Code);
		System.out.println("Date : " + Date);
		System.out.println("idtf : " + idtf);
		
		// set search vo
		listsearchVO.setSearch_by_name(Name);
		listsearchVO.setFindCode(Code);
		listsearchVO.setDate(Date);
		listsearchVO.setIncdt_idtf_cd(idtf);

		// get list or vo
		List<ActivityVO> selectActivity = null;
		List<TaskDataVO> taskData = null;

		// get act list (if list size > 1 -> throw exception) because, just one activity at a day
		if (Name != null && !(Name.equals("")) && Date != null && !(Date.equals(""))) {
			selectActivity = activityService.getActBySearchVO(listsearchVO);
		}
		
		System.out.println("act size : " + selectActivity.size());
		
		// throw exception
		if (selectActivity.size() > 1)
			scriptWriter.getInstance().printScript(response, "<script>alert('같은 데이터가 2개 이상 존재합니다. 관리자에게 문의하십시오.'); history.back();</script>", true);

		if (selectActivity != null && selectActivity.size() == 1) {
			taskData = taskDataService.getTaskDataListById(selectActivity.get(0).getId());
		}

		System.out.println("taskData : " + taskData);
		
		if (taskData != null)
			System.out.println("taskData size : " + taskData.size());

		// mapping view to jsp
		if (taskData != null)
			model.addAttribute("taskData", taskData.get(0));
		else
			model.addAttribute("taskData", null);

		model.addAttribute("listsearchVO", listsearchVO);
		System.out.println("mod taskData");
		
		return "forward:/EnterComGuide.do";
	}
	
	@RequestMapping(value = "EnterComGuide_InsertAll.do")
	public String EnterComGuide_InsertAll(HttpServletRequest request, ModelMap model) throws Exception {
		
		String guidnc_1 = (String) request.getParameter("guidnc_1");
		String guidnc_2 = (String) request.getParameter("guidnc_2");
		String guidnc_3 = (String) request.getParameter("guidnc_3");
		String date = (String) request.getParameter("date");
		String id = (String) request.getParameter("id");
		
		System.out.println("date : " + date);
		date = date.replace("-", "");
		System.out.println("date after replace : " + date);
		
		TaskDataVO taskData = new TaskDataVO();
		
		taskData.setGuidnc_1(guidnc_1);
		taskData.setGuidnc_2(guidnc_2);
		taskData.setGuidnc_3(guidnc_3);
		taskData.setId(id);
		
		taskDataService.updateCommanderGuideById(taskData);
		//Name = URLEncoder.encode(Name,"UTF-8");
		date = URLEncoder.encode(date,"UTF-8");
		
		model.addAttribute("returnUrl", "TroopsMonitoring_Search.do?incdt_idtf_cd=&date=" + date);
		model.addAttribute("RST", "CommandModifyOK");
		return "message";
	}
	
	@RequestMapping(value = "EnterComGuide_Delete1.do")
	public String EnderComGuide_Delete1(HttpServletRequest request, ModelMap model) throws Exception {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		System.out.println("id : " + id);

		TaskDataVO taskDataVO = new TaskDataVO();
		taskDataVO.setId(id);

		taskDataService.updateCommanderGuide1_ById(taskDataVO);

		model.addAttribute("returnUrl", "Guide_Insert.do?id=" + id);
		model.addAttribute("RST", "CommandModifyOK");
		return "message";
	}

	@RequestMapping(value = "EnterComGuide_Delete2.do")
	public String EnderComGuide_Delete2(HttpServletRequest request, ModelMap model) throws Exception {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		System.out.println("id : " + id);

		TaskDataVO taskDataVO = new TaskDataVO();
		taskDataVO.setId(id);

		taskDataService.updateCommanderGuide2_ById(taskDataVO);

		model.addAttribute("returnUrl", "Guide_Insert.do?id=" + id);
		model.addAttribute("RST", "CommandModifyOK");
		return "message";
	}

	@RequestMapping(value = "EnterComGuide_Delete3.do")
	public String EnderComGuide_Delete3(HttpServletRequest request, ModelMap model) throws Exception {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		System.out.println("id : " + id);

		TaskDataVO taskDataVO = new TaskDataVO();
		taskDataVO.setId(id);

		taskDataService.updateCommanderGuide3_ById(taskDataVO);
		
		model.addAttribute("returnUrl", "Guide_Insert.do?id=" + id);
		model.addAttribute("RST", "CommandModifyOK");
		return "message";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/InfomationAjax.do", produces="text/html; charset=utf-8") //인코딩 문제 해결
	@ResponseBody //리턴하기 위해 추가
	public String InfomationAjax(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
			//System.out.println("Controller > jsonString : "+jsonString);
		//jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			//System.out.println("Controller > jsonString.replace() : "+jsonString);
		
		// 1. get request ugcy, act, chklist
		int cntRqstUgcy = checkApprovalService.getNewCount(((UserVO)httpSession.getAttribute("loginedUser")));
		int cntRqstAct = requestActivityService.getNewCount(((UserVO)httpSession.getAttribute("loginedUser")));
		int cntRqstChk = requestChecklistService.getNewCount(((UserVO)httpSession.getAttribute("loginedUser")));
		
		//JSONParser parser = new JSONParser();
		//JSONObject json = (JSONObject) parser.parse(jsonString); //jsonString을 json객체로 변경
				
		JSONObject json = new JSONObject();
		json.put("cntRqstUgcy", cntRqstUgcy);
		json.put("cntRqstAct", cntRqstAct);
		json.put("cntRqstChk", cntRqstChk);

		String jsonResult = json.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		return jsonResult; //ajax의 result로 리턴됨
	}


	
	@RequestMapping(value = "Infomation.do")
	public String Infomation(HttpServletResponse response, HttpSession httpSession, ModelMap model) throws Exception {
		
		// 1. get request ugcy, act, chklist
		int cntRqstUgcy = checkApprovalService.getNewCount(((UserVO)httpSession.getAttribute("loginedUser")));
		int cntRqstAct = requestActivityService.getNewCount(((UserVO)httpSession.getAttribute("loginedUser")));
		int cntRqstChk = requestChecklistService.getNewCount(((UserVO)httpSession.getAttribute("loginedUser")));
		
		// 2. model view
		model.addAttribute("cntRqstUgcy", cntRqstUgcy);
		model.addAttribute("cntRqstAct", cntRqstAct);
		model.addAttribute("cntRqstChk", cntRqstChk);
		
		return "./main/Infomation";
	}
	
	@RequestMapping(value = "CodeTemplateExcelDownload.do")
	public void CodeTemplateExcelDownload(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mainPath = MainController.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
	    System.out.println(mainPath); //--> 절대 경로가 출력됨
	    File fileForPath = new File(mainPath);
	    String canPath = fileForPath.getCanonicalPath();
	    System.out.println("file can path : " + canPath);
	    
	    File file = new File(fileForPath.getCanonicalPath() + "/../../../../files/codeExcelTemplate.xlsx");

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
		String fileName = null;

		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),	"iso-8859-1");
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";");

		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream so = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(so);

		byte[] data = new byte[2048];
		int input = 0;
		while ((input = bis.read(data)) != -1) {
			bos.write(data, 0, input);
			bos.flush();
		}

		if (bos != null)
			bos.close();
		if (bis != null)
			bis.close();
		if (so != null)
			so.close();
		if (fis != null)
			fis.close();
	}
	
	@RequestMapping(value = "ChecklistTemplateExcelDownload.do")
	public void ChecklistTemplateExcelDownload(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mainPath = MainController.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
	    System.out.println(mainPath); //--> 절대 경로가 출력됨
	    File fileForPath = new File(mainPath);
	    String canPath = fileForPath.getCanonicalPath();
	    System.out.println("file can path : " + canPath);
	    
	    File file = new File(fileForPath.getCanonicalPath() + "/../../../../files/checklistExcelTemplate.xlsx");

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
		String fileName = null;

		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),	"iso-8859-1");
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";");

		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream so = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(so);

		byte[] data = new byte[2048];
		int input = 0;
		while ((input = bis.read(data)) != -1) {
			bos.write(data, 0, input);
			bos.flush();
		}

		if (bos != null)
			bos.close();
		if (bis != null)
			bis.close();
		if (so != null)
			so.close();
		if (fis != null)
			fis.close();
	}
	
	@RequestMapping(value = "TroopsMonitoring_Task_Detail.do")
	public String TroopsMonitoring_Task_Detail(
			@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, @ModelAttribute("activityVO") ActivityVO activityVO,
			@ModelAttribute("taskDataVO") TaskDataVO taskDataVO,
			HttpSession httpSession, 
			ModelMap mod) throws Exception {
		System.out.println(listsearchVO.getActId());
		System.out.println(listsearchVO.getSrvno());
		listsearchVO.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd()); // 사용자의 부대코드
		
		TaskDataVO taskData = null;
		TaskDataVO forSearch = null;
		
		if (listsearchVO.getAfter_rqst_data_seq() != null && !(listsearchVO.getAfter_rqst_data_seq().trim().equals(""))) {
			taskData = taskDataService.getTaskDataBySeq(listsearchVO.getAfter_rqst_data_seq());
		}
		else {
			// temp data for search
			forSearch = new TaskDataVO();
			forSearch.setId(listsearchVO.getActId());
			forSearch.setTask_psnchnrg_srvno(listsearchVO.getSrvno());
			forSearch.setIncdt_idtf_cd(((UserVO)httpSession.getAttribute("loginedUser")).getIncdt_idtf_cd());
			System.out.println("forsearch : " + forSearch.getId());
			System.out.println("forsearch : " + forSearch.getTask_psnchnrg_srvno());
			System.out.println("forsearch : " + forSearch.getIncdt_idtf_cd());
			
			taskData = taskDataService.getTaskDataVO(forSearch);
		}
		
		System.out.println("task : " + taskData);		
		
		mod.addAttribute("taskData", taskData);

		return "./main/TroopsMonitoring_Task_Detail";
	}
	
	// 지휘관 지침 입력창
	@RequestMapping(value = "Guide_Insert.do")
	public String Guide_Insert(@ModelAttribute("listsearchVO") ListsearchVO listsearchVO, HttpServletResponse response, HttpServletRequest request, ModelMap model) throws Exception {
		// search task by actID
		String id = (String) request.getParameter("id");
		
		ActivityVO actVO = activityService.getActivityById(id);
		List<TaskDataVO> taskList = taskDataService.getTaskDataListById(id);
		
		System.out.println("act vo : " + actVO);
		System.out.println("taskList : " + taskList);
		
		if (actVO == null) {
			scriptWriter.getInstance().printScript(response, "<script>alert('해당 활동을 찾을 수 없습니다. 관리자에게 문의하십시오.'); history.back();</script>", true);
			return null;
		}
		
		if (taskList == null) {
			scriptWriter.getInstance().printScript(response, "<script>alert('해당 활동내에 과업 담당자가 정해지지 않았습니다. 과업을 부여해주시기 바랍니다.'); history.back();</script>", true);
			return null;
		}
		else {
			model.addAttribute("actVO", actVO);
			model.addAttribute("taskData", taskList.get(0));
		}
		
		return "./main/Guide_Insert";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/SearchMiddleAjax.do", produces="text/html; charset=utf-8") //인코딩 문제 해결
	@ResponseBody //리턴하기 위해 추가
	public String SearchMiddleAjax(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
			System.out.println("Controller > jsonString : "+jsonString);
		jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			System.out.println("Controller > jsonString.replace() : "+jsonString);		   
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String large = (String)json.get("Large");					//jsonString 에서 받은 체크리스트 이름 확인
		System.out.println(large);
		
		List<CtlgCodeVO> ctlgListByLarge = ctlgCodeService.getCtlgCodeListByLarge(large);
		System.out.println("ajax list" + ctlgListByLarge.size());
		
		JSONArray jsonArray = new JSONArray();
		if (ctlgListByLarge != null) {
			for (int i = 0; i < ctlgListByLarge.size(); ++i) {
				JSONObject jsonTemp = new JSONObject();
				jsonTemp.put("index", i);
				jsonTemp.put("ctlg_cd", ctlgListByLarge.get(i).getCtlg_cd());
				jsonTemp.put("ctlg_nm", ctlgListByLarge.get(i).getCtlg_nm());
				jsonTemp.put("lrgcls", ctlgListByLarge.get(i).getLrgcls());
				jsonTemp.put("mdcls", ctlgListByLarge.get(i).getMdcls());
				jsonTemp.put("lrgcls_nm", ctlgListByLarge.get(i).getLrgcls_nm());
				jsonTemp.put("mdcls_nm", ctlgListByLarge.get(i).getMdcls_nm());
				
				jsonArray.add(jsonTemp);
				System.out.println("index : " + i);
			}
		}				

		String jsonResult = jsonArray.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println("json : " + jsonResult);
		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/SearchActivityAjax.do", produces="text/html; charset=utf-8") //인코딩 문제 해결
	@ResponseBody //리턴하기 위해 추가
	public String SearchActivityAjax(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
			System.out.println("Controller > jsonString : "+jsonString);
		jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			System.out.println("Controller > jsonString.replace() : "+jsonString);		   
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String middle = (String)json.get("Middle");					//jsonString 에서 받은 체크리스트 이름 확인
		System.out.println(middle);
		
		List<CtlgCodeVO> ctlgListByLarge = ctlgCodeService.getCtlgCodeListByMiddle(middle);
		System.out.println("ajax list" + ctlgListByLarge.size());
		
		JSONArray jsonArray = new JSONArray();
		if (ctlgListByLarge != null) {
			for (int i = 0; i < ctlgListByLarge.size(); ++i) {
				JSONObject jsonTemp = new JSONObject();
				jsonTemp.put("index", i);
				jsonTemp.put("ctlg_cd", ctlgListByLarge.get(i).getCtlg_cd());
				jsonTemp.put("ctlg_nm", ctlgListByLarge.get(i).getCtlg_nm());
				jsonTemp.put("lrgcls", ctlgListByLarge.get(i).getLrgcls());
				jsonTemp.put("mdcls", ctlgListByLarge.get(i).getMdcls());
				jsonTemp.put("lrgcls_nm", ctlgListByLarge.get(i).getLrgcls_nm());
				jsonTemp.put("mdcls_nm", ctlgListByLarge.get(i).getMdcls_nm());
				
				jsonArray.add(jsonTemp);
				System.out.println("index : " + i);
			}
		}				

		String jsonResult = jsonArray.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println("json : " + jsonResult);
		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@RequestMapping(value = "Checklist_Insert.do")
	public String Checklist_Insert(HttpServletRequest request) throws Exception {
		String resultCode = (String) request.getParameter("result");
		System.out.println("resultCode : " + resultCode);
		return "redirect:/ManageCheckListItem_Insert.do";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/CheckDuplicateAjax.do", produces="text/html; charset=utf-8")
	@ResponseBody //리턴하기 위해 추가
	public String CheckDuplicateAjax(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
			System.out.println("Controller > jsonString : "+jsonString);
		jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			System.out.println("Controller > jsonString.replace() : "+jsonString);		   
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String cd = (String)json.get("cd");					//jsonString 에서 받은 체크리스트 이름 확인
		System.out.println(cd);
					
		CodeVO tempCodeVO = codeService.getCodeVO(cd);
		System.out.println("MainController > Ajax > tempCodeVO : " + tempCodeVO);
		
		if (tempCodeVO != null) {
			json.put("onDuplicate", true);
		}
		else {
			json.put("onDuplicate", false);
		}

		String jsonResult = json.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println("json : " + jsonResult);
		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@RequestMapping(value = "RequestApproval_Detail.do")
	public String RequestApproval_Detail(HttpServletRequest request, ModelMap model) throws Exception {
		// date, item, srvno
		String date = (String) request.getParameter("date");
		String item = (String) request.getParameter("item");
		String srvno = (String) request.getParameter("srvno");
		
		System.out.println("date : " + date);
		System.out.println("item : " + item);
		System.out.println("srvno : " + srvno);
		
		ListsearchVO listsearchVO = new ListsearchVO();
		listsearchVO.setDate(date);
		listsearchVO.setFindCode(item);
		listsearchVO.setSrvno(srvno);
		
		CheckApprovalVO appVO = checkApprovalService.getApprovalByTaskData(listsearchVO);
		System.out.println("app : " + appVO);
		
		model.addAttribute("rqstUgcy", appVO);		
		
		return "./main/RequestApproval_Detail";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/SearchByConditionAjax.do", produces="text/html; charset=utf-8")
	@ResponseBody //리턴하기 위해 추가
	public String SearchByConditionAjax(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
			System.out.println("Controller > jsonString : "+jsonString);
		jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			System.out.println("Controller > jsonString.replace() : "+jsonString);		   
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String large = (String)json.get("large");					//jsonString 에서 받은 체크리스트 이름 확인
		String middle = (String)json.get("middle");
		String selectedCondition = (String)json.get("selectedCondition");
		String textCondition = (String)json.get("textCondition");
		System.out.println(large + " / " + middle + " / " + selectedCondition + " / " + textCondition);
		
		// get list by condition
		CtlgCodeVO tempCtlg = new CtlgCodeVO();
		tempCtlg.setLrgcls(large);
		tempCtlg.setMdcls(middle);
		if (selectedCondition.equals("ctlg_cd"))
			tempCtlg.setCtlg_cd(textCondition);
		else if(selectedCondition.equals("ctlg_nm"))
			tempCtlg.setCtlg_nm(textCondition);
		
		System.out.println("vo : " + tempCtlg.getCtlg_cd() + " / " + tempCtlg.getCtlg_nm());
		
		List<CtlgCodeVO> resultList = ctlgCodeService.getCtlgCodeByAllCondition(tempCtlg);
		System.out.println("result list : " + resultList);
		// write json array
		JSONArray jsonArray = new JSONArray();
		if (resultList != null) {
			for (int i = 0; i < resultList.size(); ++i) {
				JSONObject jsonTemp = new JSONObject();
				jsonTemp.put("index", i);
				jsonTemp.put("ctlg_cd", resultList.get(i).getCtlg_cd());
				jsonTemp.put("ctlg_nm", resultList.get(i).getCtlg_nm());
				jsonTemp.put("lrgcls", resultList.get(i).getLrgcls());
				jsonTemp.put("mdcls", resultList.get(i).getMdcls());
				jsonTemp.put("lrgcls_nm", resultList.get(i).getLrgcls_nm());
				jsonTemp.put("mdcls_nm", resultList.get(i).getMdcls_nm());
				
				jsonArray.add(jsonTemp);
			}
		}

		// return json string
		String jsonResult = jsonArray.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println("json : " + jsonResult);
		return jsonResult; //ajax의 result로 리턴됨
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="SelectLargeAjax.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String SelectLargeAjax(
			@ModelAttribute("taskVO") TaskVO  taskVO,
			@ModelAttribute("CheckCodeVO") CheckCodeVO checkcodeVO,
			@ModelAttribute("membersearchVO") MemberSearchVO membersearchVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model
			) throws Exception {
		
				String Large = request.getParameter("Large");
				System.out.println("Large : " + Large);

				String jsonString = request.getParameter("jsonString");		 //request를 사용하여 jsonString 저장
				   
				jsonString = jsonString.replaceAll("&quot;", "\""); 			//쌍따옴표 복구
				   
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
					   
						   
				membersearchVO.setSearchConditionLarge(Large);
				taskVO.setLrgcls(Large);
				
				List<TaskVO> taskLarge;
				List<TaskVO> taskMiddle;
				List<TaskVO> taskSmall;
				
				if (Large.equals(""))
				{
					membersearchVO.setEnd(1);
					taskLarge = taskService.getTaskLarge(membersearchVO);
					taskMiddle = taskService.getTaskMiddle(membersearchVO);
					taskSmall 	= taskService.getTaskSearchOption(taskVO);
				}
				else
				{
				taskLarge 	= taskService.getTaskLarge(membersearchVO);
				taskMiddle 	= taskService.getTaskMiddle(membersearchVO);
				taskSmall 	= taskService.getTaskSearchOption(taskVO); 
				}
				
				int largelenth = taskLarge.size();
				int length = taskMiddle.size();
				int Smalllength = taskSmall.size();
				
					String newResult = "";
					JSONArray jaLarge = new JSONArray();
					JSONArray jaMiddle = new JSONArray();
					JSONArray jaSmall = new JSONArray();

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
					for(int i=0; i<Smalllength; i++){
						JSONObject joSmall = new JSONObject();
						String ctlg_cd = taskSmall.get(i).getCtlg_cd();
						String ctlg_nm = taskSmall.get(i).getCtlg_nm();
						
						joSmall.put("ctlg_cd", ctlg_cd);
						joSmall.put("ctlg_nm", ctlg_nm);
						joSmall.put("lrgcls", taskSmall.get(i).getLrgcls());
						joSmall.put("mdcls", taskSmall.get(i).getMdcls());
						
						jaSmall.add(joSmall);
					}
					
				JSONObject resultjson = new JSONObject();
				resultjson.put("Middle"	, jaMiddle);
				resultjson.put("Small", jaSmall);
				
				
				String jsonResult = resultjson.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
				
				return jsonResult; //ajax의 result로 리턴됨
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/CheckDuplicateGroupCodeAjax.do", produces="text/html; charset=utf-8")
	@ResponseBody //리턴하기 위해 추가
	public String CheckDuplicateGroupCodeAjax(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String jsonString = request.getParameter("jsonString"); //request를 사용하여 jsonString 저장
			System.out.println("Controller > jsonString : "+jsonString);
		jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
			System.out.println("Controller > jsonString.replace() : "+jsonString);		   
		   
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);		//jsonString을 json으로 변경
		
		String grp_cd = (String)json.get("grp_cd");					//jsonString 에서 받은 체크리스트 이름 확인
		System.out.println(grp_cd);
					
		GroupCodeVO groupCodeVO = groupCodeService.getGroupCodeVO(grp_cd);
		System.out.println("MainController > Ajax > groupCodeVO : " + groupCodeVO);
		
		if (groupCodeVO != null) {
			json.put("onDuplicate", true);
		}
		else {
			json.put("onDuplicate", false);
		}

		String jsonResult = json.toString(); //리턴하기위해 json객체를 jsonResult 스트링으로 변경
		System.out.println("json : " + jsonResult);
		return jsonResult; //ajax의 result로 리턴됨
	}
	
	@RequestMapping(value = "logoutAjax.do")
	@ResponseBody
	public String logoutAjax(HttpSession session) throws Exception {
		System.out.println("MainController > logoutAjax.do");
		if(session.getAttribute("loginedUser") != null) {
			session.invalidate();
		}					
		
		return null;
	}
	
	@RequestMapping(value = "ItemCodeCheckAjax.do")
	@ResponseBody
	public String ItemCodeCheckAjax(HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println("MainController > ItemCodeCheckAjax.do");
		
		// parse json String
		String jsonString = request.getParameter("jsonString");
		jsonString = jsonString.replaceAll("&quot;", "\""); //쌍따옴표 복구
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		String ctlg_cd = (String)json.get("ctlg_cd");
		
		// get max item code
		ChecklistItemVO checklistItemVO = checklistItemService.getMaxItemCode(ctlg_cd);
		System.out.println("MainController > ItemCodeCheckAjax > checklistItemVO : " + checklistItemVO);
		
		// setting next code
		String nextCode = "";
		if (checklistItemVO == null) {
			nextCode = ctlg_cd + "_" + "001";
		}
		else {
			System.out.println("item code : " + checklistItemVO.getCtlg_itm_cd());
			
			String maxCode = checklistItemVO.getCtlg_itm_cd().trim();
			String[] splitCode = maxCode.split("_");
			
			System.out.println("split 0 : " + splitCode[0] + " / split 1 : " + splitCode[1]);
			
			int splitBack = Integer.parseInt(splitCode[1]);
			splitCode[1] = Integer.toString(splitBack + 1);
			if (splitBack < 100) {
				if (splitBack < 10) {
					splitCode[1] = "0" + splitCode[1];
				}
				splitCode[1] = "0" + splitCode[1];
			}			
			
			nextCode = splitCode[0] + "_" + splitCode[1];
			
			System.out.println("MainController > ItemCodeCheckAjax > nextCode : " + nextCode);
		}
		
		// check on duplicate
		ChecklistItemVO duplVO = checklistItemService.getCtlgCodeByItemCode(nextCode);		
		
		if (nextCode != "" && duplVO == null) {
			// write json data
			json.put("nextCode", nextCode);
		}
		
		// json to string for json result
		String jsonResult = json.toString();
		System.out.println("json : " + jsonResult);
		return jsonResult;
	}
}
