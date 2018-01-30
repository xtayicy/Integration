package harry.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author harry
 * @param <T>
 */
@Transactional
public abstract class BaseService<T extends IdEntity> implements IBaseService<T> {
	
	protected abstract IBaseDao<T> getBaseDao();
	
	@Override
	public void insert(T entity) throws RuntimeException{
		getBaseDao().insert(entity);
	}
	
	@Override
	public List<T> selectAll() {
		
		return getBaseDao().selectAll();
	}
	
	@Override
	public void update(T entity) throws RuntimeException {
		getBaseDao().updateByPrimaryKey(entity);
	}
}
