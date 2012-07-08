package org.aio.support;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author polly
 * @since 4.0.0
 * @version $Revision 1.1$
 */
public class Pagination {

	private static final Log log = LogFactory.getLog(Pagination.class);

	private static int DEFAULT_MAX_SIZE = 100;
	private static int DEFAULT_PAGE_SIZE = 20;//每页显示数目
	
	private int offset = 5;
	private int page = 1;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int maxPage = 1;
	private int recordSize = 0;
	private String queryString = null;
	private String actionName = null;
	private String requestString = null;
	private Map<String, String[]> paramMap = new HashMap<String, String[]>();

	public Pagination() {
		if (log.isDebugEnabled()) {
		}
	}

	public Pagination(Integer page) {
		this();
		setPage(page==null?1:page);
	}

	@SuppressWarnings("unchecked")
	public Pagination(Integer page , HttpServletRequest request) {
		this();
		setPage(page==null?1:page);
		setActionName(request.getRequestURI());
		setParamMap((Map<String, String[]>)request.getParameterMap());
	}
	
	public Pagination(Integer page, Integer pageSize) {
		this(page);
		setPageSize(pageSize==null?DEFAULT_PAGE_SIZE:pageSize);
	}

	public Pagination(Integer page, Integer pageSize, Integer recordSize) {
		this(page, pageSize);
		this.recordSize = recordSize==null?0:recordSize;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionName() {
		if (this.actionName == null) {
			this.actionName = "/";
		}
		return this.actionName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int size) {
		if (size >= 1 && size <= DEFAULT_MAX_SIZE) {
			this.pageSize = size;
		}
	}

	public int getFirst() {
		return (page - 1) * pageSize;
	}

	public int getPage() {
		if (this.page > getMaxPage()) {
			this.page = getMaxPage();
		}
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			page = 1;
		}
		this.page = page;
	}

	public int getMaxPage() {
		this.maxPage = recordSize / pageSize;
		if (recordSize % pageSize > 0) {
			this.maxPage++;
		}

		this.maxPage = this.maxPage < 1 ? 1 : this.maxPage;

		return this.maxPage;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public Map<String, String[]> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String[]> paramMap) {
		this.paramMap = paramMap;
	}

	public void addParam(String paramName, String[] paramValues) {
		this.paramMap.put(paramName, paramValues);
	}

	public void addParam(String paramName, String paramValue) {
		addParam(paramName, new String[] { paramValue });
	}

	public boolean isFirstPage() {
		return (this.page == 1);
	}

	public boolean isLastPage() {
		return (this.page >= getMaxPage());
	}

	public boolean hasNextPage() {
		return !isLastPage();
	}

	public boolean hasPrevPage() {
		return !isFirstPage();
	}

	public boolean hasResult() {
		return recordSize > 0;
	}

	public String getQueryString() {
		if (this.queryString == null) {
			StringBuffer sb = new StringBuffer("");
			if (paramMap != null && paramMap.size() > 0) {
				for (Iterator<String> iter = paramMap.keySet().iterator(); iter
						.hasNext();) {
					String key = iter.next();
					String[] values = paramMap.get(key);
					for (int i = 0; i < values.length; i++) {
						if (!("page".equals(key)||"pageSize".equals(key)||"pageNum".equals(key)||"numPerPage".equals(key))) {
							try {
								if("".equals(new String(values[0].getBytes("ISO-8859-1"), "utf-8"))){
									continue;
								}
								sb.append(key).append("=").append(new String(values[0].getBytes("ISO-8859-1"), "utf-8")).append("&");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			this.queryString = sb.toString();
		}
		return this.queryString;
	}

	public String getRequestString() {
		if (this.requestString == null) {
			StringBuffer sb = new StringBuffer("");
			sb.append(getActionName()).append("?").append(getQueryString());
			this.requestString = sb.toString();
		}
		return this.requestString;
	}

	public String getPageToDisplay() {
		// 如果没有结果，则不显示任何东西
		if (!hasResult()) {
			if (log.isDebugEnabled()) {
				log.debug("没有结果。recordsize=" + recordSize);
			}
			return "";
		}

		int maxPage = getMaxPage();

		StringBuffer sb = new StringBuffer();
		if(page>maxPage){
			page=maxPage;
		}
		sb.append("<div class='pagination'>");
		if (isFirstPage()) {
			sb.append("<span class=\"nextprev\">上一页  </span>");
		} else {
			sb.append("<a class=\"nextprev\" href='")
					.append(getRequestString()).append("page=").append(
							this.page - 1).append("'>").append("上一页  ").append(
							"</a>");
		}

		if (page - offset < 2) {
			// 1 2 3 4 page
			for (int i = 1; i < page; i++) {
				sb.append("<a href='").append(getRequestString()).append(
						"page=").append(i).append("'>").append("  " + i + "  ")
						.append("</a>");
			}
		} else {
			// 1...3 4 5 6 page
			sb.append("<a href='").append(getRequestString()).append("page=")
					.append(1).append("'>").append("  " + 1 + "  ").append(
							"</a>");
			sb.append("...");
			for (int i = page - offset; i < page; i++) {
				sb.append("<a href='").append(getRequestString()).append(
						"page=").append(i).append("'>").append("  " + i + "  ")
						.append("</a>");
			}
		}

		sb.append("<span class=\"current\">").append("  " + page + "  ")
				.append("</span>");

		if (page + offset + 1 > maxPage) {
			// page 7 8 9
			for (int i = page + 1; i <= maxPage; i++) {
				sb.append("<a href='").append(getRequestString()).append(
						"page=").append(i).append("'>").append("  " + i + "  ")
						.append("</a>");
			}
		} else {
			// page 7 8 9 10 ... 100
			for (int i = page + 1; i <= page + offset; i++) {
				sb.append("<a href='").append(getRequestString()).append(
						"page=").append(i).append("'>").append("  " + i + "  ")
						.append("</a>");
			}
			sb.append("<span class=\"break\">...</span>");
			sb.append("<a href='").append(getRequestString()).append("page=")
					.append(maxPage).append("'>").append("  " + maxPage + "  ")
					.append("</a>");
		}

		if (isLastPage()) {
			sb.append("<span class=\"nextprev\"> 下一页</span>");
		} else {
			sb.append("<a class=\"nextprev\" href='")
					.append(getRequestString()).append("page=").append(
							this.page + 1).append("'>").append(" 下一页").append(
							"</a>");
		}
		sb.append(" 总共有 "+recordSize+" 条记录,");
		sb.append(" <select onchange=\"location.href=");
		sb.append("this.value");
		sb.append("\">");
		int cuts[]={10,15,20,25,30,50,100};
		for (int i : cuts) {
			sb.append("<option value='");
			sb.append(getRequestString()+"pageSize="+i);
			sb.append("'");
			if(i==pageSize)
				sb.append("selected='selected'");
			sb.append(">"+i);
			sb.append("</option>");
		}
		sb.append("</select>\r\n");
		sb.append(" 条/页");
//		sb.append(" 转到:<input type='text' maxlength=4 size='4' id='page' value='");
//		sb.append(page);
//		sb.append("'/>");
//		sb.append(" <button onclick='goto();' class='button'>跳转</button>");
//		sb.append(" <script language='javascript'>");
//		sb.append(" function goto(){");
//		sb.append("  var page=document.getElementById('page').value;");
//		sb.append(" location.href='");
//		sb.append(getRequestString());
//		sb.append("page='+page;");
//		sb.append(" }");
//		sb.append("</script>");
		sb.append("</div>");
		return sb.toString();
	}

	public String toString() {
		return this.getPageToDisplay();
	}

}
