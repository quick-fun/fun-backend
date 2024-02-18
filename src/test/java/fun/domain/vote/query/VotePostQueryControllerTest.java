//package fun.domain.vote.query;
//
//import fun.domain.vote.query.response.MemberProfileResponse;
//import fun.domain.vote.query.response.TagResponse;
//import fun.domain.vote.query.response.VoteItemResponse;
//import fun.domain.vote.query.response.VoteLabelResponse;
//import fun.domain.vote.query.response.VotePostDetailResponse;
//import fun.domain.vote.query.response.VotePostPageResponse;
//import fun.testconfig.ControllerTestConfig;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static fun.ApiUrl.GET_VOTE_POST_DETAIL;
//import static fun.ApiUrl.GET_VOTE_POST_PAGE;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class VotePostQueryControllerTest extends ControllerTestConfig {
//
//    @DisplayName("[SUCCESS] 투표 게시글을 상세 조회한다.")
//    @Test
//    void success_readVotePostDetailByVotePostId() throws Exception {
//        // when
//        when(
//                votePostQueryService.findVotePostDetailByVotePostId(any(Long.class))
//        ).thenReturn(
//                new VotePostDetailResponse(
//                        1L,
//                        "투표 게시글 제목",
//                        "투표 게시글 내용",
//                        List.of(
//                                new VoteItemResponse(
//                                        1L,
//                                        "투표 항목 내용1",
//                                        0
//                                ),
//                                new VoteItemResponse(
//                                        2L,
//                                        "투표 항목 내용2",
//                                        100
//                                )
//                        ),
//                        new TagResponse(
//                                1L,
//                                "태그명"
//                        ),
//                        List.of(
//                                new VoteLabelResponse(
//                                        1L,
//                                        "라벨명"
//                                )
//                        ),
//                        LocalDateTime.now(),
//                        new MemberProfileResponse(
//                                1L,
//                                "사용자 닉네임",
//                                "사용자 메달명",
//                                "사용자 이미지 url"
//                        )
//                )
//        );
//
//        // then
//        mockMvc.perform(
//                        get(GET_VOTE_POST_DETAIL, 1L)
//                                .pathInfo("/votePostId")
//                                .contentType(APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andDo(restDocs.document(
//                        pathParameters(
//                                parameterWithName("votePostId").description("투표 게시글 식별자값")
//                        ),
//                        responseFields(
//                                fieldWithPath("votePostId").type(String.class).description("투표 게시글 식별자값"),
//                                fieldWithPath("title").type(String.class).description("투표 게시글 제목"),
//                                fieldWithPath("content").type(String.class).description("투표 게시글 내용"),
//                                fieldWithPath("voteItems.[].voteItemId").type(Number.class).description("투표 항목 식별자값"),
//                                fieldWithPath("voteItems.[].voteItemTitle").type(String.class).description("투표 항목 내용"),
//                                fieldWithPath("voteItems.[].voteRate").type(Number.class).description("투표 항목 비율"),
//                                fieldWithPath("tag.tagId").type(Number.class).description("태그 식별자값"),
//                                fieldWithPath("tag.tagTitle").type(String.class).description("태그명"),
//                                fieldWithPath("labels.[].labelId").type(Number.class).description("라벨 식별자값"),
//                                fieldWithPath("labels.[].labelTitle").type(String.class).description("라벨명"),
//                                fieldWithPath("createdAt").type(LocalDateTime.class).description("투표 게시글 작성일자"),
//                                fieldWithPath("profile.memberId").type(Number.class).description("작성자(사용자) 식별자값"),
//                                fieldWithPath("profile.nickname").type(String.class).description("작성자(사용자) 닉네임"),
//                                fieldWithPath("profile.memberMedalTitle").type(String.class).description("작성자(사용자) 메달명"),
//                                fieldWithPath("profile.imageUrl").type(String.class).description("작성자(사용자) 이미지 url")
//                        )
//                ));
//    }
//
//    @DisplayName("[SUCCESS] 투표 게시글 페이징 목록을 조회한다.")
//    @Test
//    void success_readVotePostPage() throws Exception {
//        // when
//        when(
//                votePostQueryService.pageVotePosts(anyLong(), anyLong())
//        ).thenReturn(
//                new VotePostPageResponse(
//                        List.of(
//                                new VotePostPageResponse.VotePostPageSubResponse(
//                                        1L,
//                                        "투표 게시글 제목",
//                                        "투표 게시글 내용",
//                                        List.of(
//                                                new VoteItemResponse(
//                                                        1L,
//                                                        "투표 항목 내용1",
//                                                        0
//                                                ),
//                                                new VoteItemResponse(
//                                                        2L,
//                                                        "투표 항목 내용2",
//                                                        100
//                                                )
//                                        ),
//                                        new TagResponse(
//                                                1L,
//                                                "태그명"
//                                        ),
//                                        List.of(
//                                                new VoteLabelResponse(
//                                                        1L,
//                                                        "라벨명"
//                                                )
//                                        ),
//                                        LocalDateTime.now(),
//                                        0L
//                                )
//                        )
//                )
//        );
//
//        // then
//        mockMvc.perform(
//                        get(GET_VOTE_POST_PAGE)
//                                .queryParam("cursor", "10")
//                                .queryParam("limit", "10")
//                                .contentType(APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andDo(restDocs.document(
//                        queryParameters(
//                                parameterWithName("cursor").description("이전 데이터의 마지막 식별자값 + 1"),
//                                parameterWithName("limit").description("원하는 데이터의 개수")
//                        ),
//                        responseFields(
//                                fieldWithPath("data.[].votePostId").type(String.class).description("투표 게시글 식별자값"),
//                                fieldWithPath("data.[].title").type(String.class).description("투표 게시글 제목"),
//                                fieldWithPath("data.[].content").type(String.class).description("투표 게시글 내용"),
//                                fieldWithPath("data.[].voteItems.[].voteItemId").type(Number.class).description("투표 항목 식별자값"),
//                                fieldWithPath("data.[].voteItems.[].voteItemTitle").type(String.class).description("투표 항목 내용"),
//                                fieldWithPath("data.[].voteItems.[].voteRate").type(Number.class).description("투표 항목 비율"),
//                                fieldWithPath("data.[].tag.tagId").type(Number.class).description("태그 식별자값"),
//                                fieldWithPath("data.[].tag.tagTitle").type(String.class).description("태그명"),
//                                fieldWithPath("data.[].labels.[].labelId").type(Number.class).description("라벨 식별자값"),
//                                fieldWithPath("data.[].labels.[].labelTitle").type(String.class).description("라벨명"),
//                                fieldWithPath("data.[].createdAt").type(LocalDateTime.class).description("투표 게시글 작성일자"),
//                                fieldWithPath("data.[].commentTotalCount").type(Number.class).description("댓글수")
//                        )
//                ));
//    }
//}
