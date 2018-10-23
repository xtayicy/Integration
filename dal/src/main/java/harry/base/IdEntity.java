package harry.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author harry
 *
 */
public class IdEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3288352616405434042L;
	private Date createTime = new Date();
	private Date deleteTime;
	private Boolean valid = true;
	private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "IdEntity [createTime=" + createTime + ", deleteTime="
				+ deleteTime + ", valid=" + valid + ", updateTime="
				+ updateTime + "]";
	}
}
