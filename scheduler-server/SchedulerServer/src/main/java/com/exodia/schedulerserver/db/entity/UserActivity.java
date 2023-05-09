package com.exodia.schedulerserver.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_activity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActivity {
	@Id
	@Column(name = "user_id", nullable = false)
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "recent_login_time")
	private LocalDateTime recentLoginTime;

	@Column(name = "recent_login_ip", length = 20)
	private String recentLoginIp;

	@Column(name = "shortest_escape_time")
	private Long shortestEscapeTime;

	@Column(name = "longest_survival_time")
	private Long longestSurvivalTime;

	@Column(name = "normal_monster_kills")
	private int normalMonsterKills;

	@Column(name = "elite_monster_kills")
	private int eliteMonsterKills;

	@Column(name = "death_count")
	private int deathCount;

	@Column(name = "total_quest_completed")
	private int totalQuestCompleted;

	@Column(name = "total_item_crafted")
	private int totalItemCrafted;

	@Column(name = "total_escape_count", nullable = false)
	private int totalEscapeCount;

	@Column(name = "total_play_time", nullable = false)
	private int totalPlayTime;

	public void changeEscapeAndDeathCnt(boolean isEscape){
		if(isEscape){
			this.totalEscapeCount++;
		}else{
			this.deathCount++;
		}
	}

	public void checkTimeToChange(Long spentTime){
		if(spentTime < shortestEscapeTime)
			this.shortestEscapeTime = spentTime;

		if(spentTime > longestSurvivalTime)
			this.longestSurvivalTime = spentTime;

		this.totalPlayTime+=spentTime;
	}

	public void increaseMonsterKills(int normal, int elite){
		this.normalMonsterKills += normal;
		this.eliteMonsterKills += elite;
	}

	public void increaseItemCraftedCnt(int cnt){
		this.totalItemCrafted+=cnt;
	}

	public void increaseQuestCompletedCnt(int cnt){
		this.totalQuestCompleted+=cnt;
	}

}