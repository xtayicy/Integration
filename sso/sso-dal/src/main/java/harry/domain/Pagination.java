package harry.domain;

import java.util.List;

/**
 * 
 * @author harry
 *
 */
public class Pagination<T> extends PaginationSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4150114537081071946L;
	private List<T> list;

	public Pagination() {
	}


	public Pagination(int pageNo, int pageSize) {
		super(pageNo, pageSize);
	}

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
}
