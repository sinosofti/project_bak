package null.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.Date;

/** IM消息记录表(IM_MESSAGE) **/
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 (Not Null) */
	private String id;
	/** 发送人 */
	private String senduser;
	/** 接收人 */
	private String receiveuser;
	/** 群ID */
	private String groupid;
	/** 是否已读 0 未读  1 已读 */
	private String isread;
	/** 类型 0 单聊消息  1 群消息 */
	private String type;
	/** 消息内容 */
	private String content;
	/** 创建人 */
	private String createuser;
	/** 创建时间 */
	private Date createdate;
	/** 修改时间 */
	private Date updatedate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenduser() {
		return senduser;
	}

	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}

	public String getReceiveuser() {
		return receiveuser;
	}

	public void setReceiveuser(String receiveuser) {
		this.receiveuser = receiveuser;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

}