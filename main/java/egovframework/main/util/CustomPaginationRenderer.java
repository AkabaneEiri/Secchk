package egovframework.main.util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class CustomPaginationRenderer extends AbstractPaginationRenderer {
	public CustomPaginationRenderer() {
		
		firstPageLabel 				= "<li class=\"page-item\"><a href=\"#\" class=\"page-link\" onclick=\"{0}({1}); return false;\"><<</a></li>"; 
		previousPageLabel 	= "<li class=\"page-item\"><a href=\"#\" class=\"page-link\" onclick=\"{0}({1}); return false;\"><</a></li>"; 
		currentPageLabel 		= "<li class=\"page-item active\"><a class=\"page-link\" href=\"#\" >{0}</a></li>"; 
		otherPageLabel 			= "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel				= "<li class=\"page-item\"><a href=\"#\" class=\"page-link\" onclick=\"{0}({1}); return false;\">></a></li>"; 
		lastPageLabel 				= "<li class=\"page-item\"><a href=\"#\" class=\"page-link\" onclick=\"{0}({1}); return false;\">>></a></li>"; 
		}
	}

