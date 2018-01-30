package harry.domain;

import java.io.Serializable;

/**
 * 
 * @author harry
 *
 */
public class PersistentObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423487116722351768L;
	
	/** 主键ID */
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 覆盖原equals方法，只要对象类型相同并且主键相同，那么返回true，表示两个对象相等
	 * 
	 * @param Object
	 *            o
	 * @return boolean
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof PersistentObject)) {
			return false;
		}
		PersistentObject other = (PersistentObject) o;
		return id.equals(other.getId());
	}

	/**
	 * 覆盖原hashCode方法，用主键的hashcode代替原来对象的hashcode，可以简化持久化对象的比较
	 * 
	 * @return int
	 */
	public int hashCode() {
		if (id == null) {
			return Integer.valueOf(0);
		}
		return id.hashCode();
	}
}
