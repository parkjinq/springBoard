package kr.or.ddit.attachments.model;

public class AttachmentsVO {
	
	private String att_id;
	private String ps_id;
	private String att_path;
	private String att_originname;

	public String getAtt_id() {
		return att_id;
	}
	public void setAtt_id(String att_id) {
		this.att_id = att_id;
	}
	public String getPs_id() {
		return ps_id;
	}
	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}
	public String getAtt_path() {
		return att_path;
	}
	public void setAtt_path(String att_path) {
		this.att_path = att_path;
	}
	public String getAtt_originname() {
		return att_originname;
	}
	public void setAtt_originname(String att_originname) {
		this.att_originname = att_originname;
	}
	@Override
	public String toString() {
		return "AttachmentsVO [att_id=" + att_id + ", ps_id=" + ps_id + ", att_path=" + att_path + ", att_originname="
				+ att_originname + "]";
	}

}
