package com.easyhz.noffice.feature.organization.util.detail

import com.easyhz.noffice.core.model.announcement.detail.AnnouncementDetail
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.task.Task


// FIXME
internal val DUMMY_ORGANIZATION_INFORMATION = OrganizationInformation(
    name = "CMC 15th",
    profileImageUrl = "",
    category = listOf("IT", "창업")
)

internal val DUMMY_LIST = listOf(
    AnnouncementDetail(
        id = 1,
        title = "5차 세션 : 최종 팀 빌딩 ~ 제목이 두 줄 일 때",
        creationDate = "2024.06.29(토) 14:00",
        organizationName = "CMC 15th",
        organizationCategory = "",
        organizationProfileImage = "",
        date = "12/31",
        place = "Meet : Google에서 제공하는 실시간 화상회의에 참여하세요.",
        placeUrl = "ww.",
        content = "6월 29일 기획 & 디자인 & 개발 최종 팀빌딩을 진행합니다. 저번 기-디 매칭 세션과 동일하게 발표 잘리면 궁금하지 이렇게 길어지면은 자를거임,",
        taskList = listOf(Task("ddsfs", false), Task("ddsfs", false))
    ), AnnouncementDetail(
        id = 2,
        title = "5차 세션 : 최종 팀빌딩",
        creationDate = "2024.06.29(토) 14:00",
        organizationName = "CMC 15th",
        organizationCategory = "",
        organizationProfileImage = "",
        date = "12/31",
        place = "",
        placeUrl = "ww.",
        content = "6월 29일 기획 & 디자인 & 개발 최종 팀빌딩을 진행합니다. 저번 기-디 매칭 세션과 동일하게 내용 궁금 텍스트가 길어지나? 이렇게 길어지면은 자를거임,",
        taskList = listOf(Task("ddsfs", false), Task("ddsfs", false), Task("ddsfs", false))
    ), AnnouncementDetail(
        id = 3,
        title = "5차 세션 : 최종 팀빌딩",
        creationDate = "2024.06.29(토) 14:00",
        organizationName = "CMC 15th",
        organizationCategory = "",
        organizationProfileImage = "",
        date = "12/31",
        place = "Meet : Google에서 제공하는 실시간 화상회의에 참여하세요.",
        placeUrl = "ww.",
        content = "6월 29일 기획 & 디자인 & 개발 최종 팀빌딩을 진행합니다. 저번 기-디 매칭 세션과 동일하게 발표할건데, 텍스트가 길어지나? 이렇게 길어지면은 자를거임,",
        taskList = emptyList()
    ),AnnouncementDetail(
        id = 4,
        title = "5차 세션 : 최종 팀빌딩",
        creationDate = "2024.06.29(토) 14:00",
        organizationName = "CMC 15th",
        organizationCategory = "",
        organizationProfileImage = "",
        date = "",
        place = "Meet : Google에서 제공하는 실시간 화상회의에 참여하세요.",
        placeUrl = "ww.",
        content = "6월 29일 기획 & 디자인 & 개발 최종 팀빌딩을 진행합니다. 저번 기-디 매칭 세션과 동일하게 발표할건데, 텍스트가 길어지나? 이렇게 길어지면은 자를거임,",
        taskList = emptyList()
    ), AnnouncementDetail(
        id = 5,
        title = "5차 세션 : 최종 팀빌딩",
        creationDate = "2024.06.29(토) 14:00",
        organizationName = "CMC 15th",
        organizationCategory = "",
        organizationProfileImage = "",
        date = "12/31",
        place = "",
        placeUrl = "ww.",
        content = "텍스트 짧게",
        taskList = listOf(Task("ddsfs", false), Task("ddsfs", false), Task("ddsfs", false))
    )
)