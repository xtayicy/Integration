package harry.base;

import java.util.List;

/**
 * 
 * @author harry
 *
 */
public interface IBaseDao<T extends IdEntity> {
	
	 void insert(T entity);
	 
	 List<T> selectAll();
	 
	 void updateByPrimaryKey(T entity);
}
