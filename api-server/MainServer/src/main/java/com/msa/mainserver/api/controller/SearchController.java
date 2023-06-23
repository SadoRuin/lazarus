package com.msa.mainserver.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.mainserver.api.service.SearchService;
import com.msa.mainserver.dto.response.FindRecordResponse;
import com.msa.mainserver.dto.response.FindUserResponse;
import com.msa.mainserver.dto.response.NoticeDetailResponse;
import com.msa.mainserver.dto.response.NoticeResponse;
import com.msa.mainserver.dto.response.RankingResponse;
import com.msa.mainserver.dto.response.StatisticsResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/search")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/user")
	public ResponseEntity<FindUserResponse> findUserData(@RequestParam("nickname") String nickname) {

		FindUserResponse userActivity = searchService.findUserActivity(nickname);
		return ResponseEntity.ok(userActivity);
	}

	@GetMapping("/record")
	public ResponseEntity<List<FindRecordResponse>> findUserRecord(@RequestParam("nickname") String nickname,
		@RequestParam("page") int page) {
		List<FindRecordResponse> userRecord = searchService.findUserRecord(nickname, page);
		return ResponseEntity.ok(userRecord);
	}

	@GetMapping("/ranking")
	public ResponseEntity<RankingResponse> getRanking() {
		RankingResponse ranking = searchService.getRanking();
		return ResponseEntity.ok(ranking);
	}

	@GetMapping("/statistics")
	public ResponseEntity<StatisticsResponse> getStatistics() {
		StatisticsResponse statistics = searchService.getStatistics();
		return ResponseEntity.ok(statistics);
	}

	@GetMapping("/notice/{page}")
	public ResponseEntity<NoticeResponse> getNotice(@PathVariable("page") int page) {
		NoticeResponse notice = searchService.getNotice(page);
		return ResponseEntity.ok(notice);
	}

	@GetMapping("/notice/detail/{no}")
	public ResponseEntity<NoticeDetailResponse> getNoticeDetail(@PathVariable("no") Long no) {
		NoticeDetailResponse noticeDetail = searchService.getNoticeDetail(no);
		return ResponseEntity.ok(noticeDetail);
	}

}
