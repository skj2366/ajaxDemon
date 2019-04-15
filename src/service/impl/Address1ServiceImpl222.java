package service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.Address1DAO;
import dao.impl.Address1DAOImpl;
import service.Address1Service;
import utils.Command;

public class Address1ServiceImpl222 implements Address1Service {

//	private Address1DAO adao = new Address1DAOImpl();
//		
//	@Override
//	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
//		Map<String,String> paramMap = Command.getSingleMap(request);
//		
//		int page = 1;
//		int pageCount =10;
//		int blockCount = 10;
//		
//		if(paramMap.get("page")!=null) {
//			page = Integer.parseInt(paramMap.get("page"));
//		}
//		if(paramMap.get("pageCount")!=null) {
//			pageCount = Integer.parseInt(paramMap.get("pageCount"));
//		}
//		if(paramMap.get("blockCount")!=null) {
//			blockCount = Integer.parseInt(paramMap.get("blockCount"));
//		}
////		String adDong = paramMap.get("ad_dong");
////		request.setAttribute("ad_dong", adDong);
//		request.setAttribute("page", page);
//		request.setAttribute("pageCount", pageCount);
//		request.setAttribute("blockCount", blockCount);
//		int lNum = page * pageCount;
//		int sNum = lNum - (pageCount-1);
//		paramMap.put("lNum", lNum+"");
//		paramMap.put("sNum", sNum+"");
//		List<Map<String,String>> addrList = adao.selectAddrList(paramMap);
//		request.setAttribute("list", addrList);
//		int totalCnt = adao.selectTotalAddrCount(paramMap);
//		request.setAttribute("totalCnt", totalCnt);
//		int totalPageCnt = totalCnt/pageCount;
//		if(totalCnt%pageCount>0) {
//			totalPageCnt ++;
//		}
////		int lBlock = ((page-1)/blockCount+1) * blockCount;
////		int fBlock = lBlock-(blockCount-1);
//		int fBlock = ((page-1)/blockCount) * blockCount + 1;
//		int lBlock = fBlock + (blockCount-1);
//		if(lBlock>totalPageCnt) {
//			lBlock = totalPageCnt;
//		}
//	
//		request.setAttribute("lBlock", lBlock);
//		request.setAttribute("fBlock", fBlock);
//		request.setAttribute("totalPageCnt",totalPageCnt);
//		return addrList;
//	}
//
//	@Override
//	public int selectTotalAddrCount() {
//		//return adao.selectTotalAddrCount();
//		return 0;
//	}
//
//	@Override
//	public void selectAddr(HttpServletRequest request) {
//		Map<String,String> paramMap = Command.getSingleMap(request);
//		int page = 1;
//		int pageCount =10;
//		
//		if(paramMap.get("page")!=null) {
//			page = Integer.parseInt(paramMap.get("page"));
//		}
//		if(paramMap.get("pageCount")!=null) {
//			pageCount = Integer.parseInt(paramMap.get("pageCount"));
//		}
//		request.setAttribute("page", page);
//		request.setAttribute("pageCount", pageCount);
//		request.setAttribute("addr", adao.selectAddr(paramMap));
//	}
	
private Address1DAO adao = new Address1DAOImpl();	
	
	@Override
	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
		Map<String,String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		int blockCount = 10;
		if(paramMap.get("page")!=null && !"".equals(paramMap.get("page"))) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if(paramMap.get("pageCount")!=null && !"".equals(paramMap.get("pageCount"))){
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		if(paramMap.get("blockCount")!=null) {
			blockCount = Integer.parseInt(paramMap.get("blockCount"));
		}
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockCount", blockCount);
		request.setAttribute("page", page);
		
		int lNum = page * pageCount;
		int sNum = lNum - (pageCount - 1);
		paramMap.put("lNum", lNum+"");
		paramMap.put("sNum", sNum + "");
		List<Map<String, String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);
		int totalCnt = adao.selectTotalAddrCount(paramMap);
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt/pageCount;
		if(totalCnt%pageCount>0) {
			totalPageCnt ++;
		}
		int lBlock = ((page-1)/blockCount+1) * blockCount;
		int fBlock = lBlock-(blockCount-1);
		if(lBlock>totalPageCnt) {
			lBlock = totalPageCnt;
		}

		request.setAttribute("lBlock", lBlock);
		request.setAttribute("fBlock", fBlock);
		request.setAttribute("totalPageCnt", totalPageCnt);
		List<String> asList = adao.selectAdSido();
		request.setAttribute("asList", asList);
		request.setAttribute("agList", adao.selectAdGugun(asList.get(0)));
		return addrList;
	}

	@Override
	public int selectTotalAddrCount() {
		//return adao.selectTotalAddrCnt();
		return 0;
	}

	@Override
	public void selectAddr(HttpServletRequest request) {
		Map<String,String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		if(paramMap.get("page")!=null) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if(paramMap.get("pageCount")!=null){
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("addr", adao.selectAddr(paramMap));
		
	}

	@Override
	public int updateAddr(Map<String, String> addr) { // 나
		return adao.updateAddr(addr);
	}

	@Override
	public Map<String, String> updateAddr2(HttpServletRequest request) throws IOException { // 선생님

		Map<String, String> addr = Command.fromJSON(request);
		Map<String, String> rMap = new HashMap<>();
		rMap.put("update", "false");
		rMap.put("msg", "수정이 실패 하였습니다.");
		if(adao.updateAddr(addr)==1) {
			rMap.put("update", "true");
			rMap.put("msg", "수정이 성공 하였습니다.");
		}
		return rMap;
	}

	@Override
	public Map<String, String> deleteAddr(HttpServletRequest request) throws IOException {
		Map<String, String> addr = Command.fromJSON(request);
		Map<String, String> rMap = new HashMap<>();
		rMap.put("update", "false");
		rMap.put("msg", "삭제를 실패 하였습니다.");
		if(adao.deleteAddr(addr)==1) {
			rMap.put("update", "true");
			rMap.put("msg", "삭제를 성공 하였습니다.");
		}
		return rMap;
	}

	@Override
	public List<String> selectAdSido() {
		return adao.selectAdSido();
	}

	@Override
	public List<String> selectAdGugun(String adSido) {
		return adao.selectAdGugun(adSido);
	}


	
}
