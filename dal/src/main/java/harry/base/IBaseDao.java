package harry.base;

import java.util.List;

/**
 * 
 * @author harry
 *
 */
public interface IBaseDao<T extends IdEntity> {
	
	 public void insert(T entity);
	 
	 public List<T> selectAll();
	 
	 public void updateByPrimaryKey(T entity);
}
