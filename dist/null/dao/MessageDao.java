package null.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import null.po.Message;

/** IM消息记录表(IM_MESSAGE) **/
public interface MessageDao {

	public Message find(Serializable id);

	public List<Message> findAll();

	public void create(Message t);

	public void update(Message t);

	public void delete(Serializable id);

	public void deleteAll();

	/** codegen **/
}