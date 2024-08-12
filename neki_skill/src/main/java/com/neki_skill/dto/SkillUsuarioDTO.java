package com.neki_skill.dto;

public class SkillUsuarioDTO {

    private Long id;
    private Long usuarioId;
    private SkillDTO skill; 
    private Integer level;
	public SkillUsuarioDTO(Long id, Long usuarioId, SkillDTO skill, Integer level) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.skill = skill;
		this.level = level;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public SkillDTO getSkill() {
		return skill;
	}
	public void setSkill(SkillDTO skill) {
		this.skill = skill;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}