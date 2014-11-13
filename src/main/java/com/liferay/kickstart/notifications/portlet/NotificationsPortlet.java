package com.liferay.kickstart.notifications.portlet;

import com.liferay.kickstart.notifications.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public class NotificationsPortlet extends MVCPortlet {

	public void sendNotification(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException, IOException {

		System.out.println("sending notification");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long userId = themeDisplay.getUserId();
		long targetUserId = userId;
		
		long timestamp = System.currentTimeMillis();
		String type = PortletKeys.NOTIFICATION_TEST_PORTLET_WAR_NOTIFICATION_TEST;
		JSONObject payloadJSONObject = getJSONPayload();
		
		NotificationEvent notificationEvent = NotificationEventFactoryUtil.createNotificationEvent(timestamp,
				type,
				payloadJSONObject);
		notificationEvent.setDeliverBy(userId);
		
		UserNotificationEventLocalServiceUtil.addUserNotificationEvent(targetUserId, notificationEvent);
		
		sendRedirect(actionRequest, actionResponse);
		
	}

	private JSONObject getJSONPayload() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("payload", "Hey There!");
		
		return jsonObject;
	}

	Log _log = LogFactoryUtil.getLog(getClass());
}
