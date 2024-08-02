package com.easyhz.noffice.feature.announcement.contract.detail

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementDetail
import com.easyhz.noffice.core.model.task.Task

data class DetailState(
    val isLoading: Boolean,
    val isShowBottomSheet: Boolean,
    val isWebViewLoading: Boolean,
    val canGoBack: Boolean,
    val detail: AnnouncementDetail
): UiState() {
    companion object {
        fun init() = DetailState(
            isLoading = true,
            isShowBottomSheet = false,
            isWebViewLoading = true,
            canGoBack = false,
            detail = AnnouncementDetail(
                title = "",
                creationDate = "",
                organizationName = "",
                organizationCategory = "",
                organizationProfileImage = "",
                date = "",
                place = "",
                placeUrl = "",
                content = "",
                taskList = emptyList()
            )
        )

        fun DetailState.updateDetailTitle(
            title: String
        ):DetailState = this.copy(
            detail = this.detail.copy(title = title)
        )
    }
}

// FIXME
internal val DUMMY = AnnouncementDetail(
    title = "5차 세션 : 최종 팀 빌딩 ~ 제목이 두 줄 일 때",
    creationDate = "2024.06.29 14:02",
    organizationName = "CMC 15th",
    organizationCategory = "IT · 창업",
    organizationProfileImage = "",
    date = "2024.07.27(토) 14:02",
    place = "서울 창업 허브 : 장소 이름이름이름이름..",
    placeUrl = "https://naver.me/IGJAhyjN",
    content = """
        15기 챌린저 전원이 함께 모여 작업할 수 있는 모각작 세션과 UT(User Test)가 진행됩니다.
        User Test는 실제 사용자가 서비스를 테스트하며 피드백하는 중요한 과정입니다. 사용자가 주어진 작업을 완료하는 데 걸리는 시간을 관찰하는 등의 방법을 통해 사용성을 평가하고, 피드백을 받아 서비스를 더욱 더 발전시킬 수 있습니다.
        사용자를 이해하는 과정을 통해 다양한 인사이트를 얻을 수 있으면 좋겠습니다.
        
        이번 모각작 UT는 Maze로 진행합니다. 프로토타입을 만들어서 Maze 링크를 만들어 배포하시면 됩니다. [단, UT 세션 진행 전에 7/27에 진행할 UT 링크를 절대 배포하지 마세요.]
    """.trimIndent(),
    taskList = listOf(
        Task(
            content = "기획/디자이너 : 테스트를 진행할 메인 기능 프로토타입 최소 1개 이상 (간단해도 괜찮으니 메인 기능은 꼭 필수로)",
            isDone = false
        ),
        Task(
            content = "기획/디자이너 : UT 진행할 기기 - 스마트폰 기기 혹은 노트북",
            isDone = false
        ),
        Task(
            content = "열정과 패기 챙겨서 열심히 임해버리기 아자아자 화이팅",
            isDone = false
        )
    )
)
