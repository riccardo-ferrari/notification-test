package com.liferay.kickstart.notifications.notification;

import com.liferay.kickstart.notifications.util.PortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;

public class NotificationsUserNotificationHandler extends
		BaseUserNotificationHandler {


	public NotificationsUserNotificationHandler() {
		setPortletId(PortletKeys.NOTIFICATION_TEST_PORTLET_WAR_NOTIFICATION_TEST);
	}
	
	@Override
	protected String getBody(UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext) throws Exception {
		JSONObject payload = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());
		return payload.getString("payload");
	}
}
