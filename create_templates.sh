#!/bin/bash

# Hardcode properties directly in the script as requested
NOTIFICATION_SERVICE_URL="https://notification-service.pynfi.com"
NOTIFICATION_SERVICE_TOKEN="0a98858a-a4ab-40c0-9d44-6239a71daed4"
FROM_EMAIL="noreply@ugate.com"

echo "Notification Service URL: $NOTIFICATION_SERVICE_URL"
echo "Notification Service Token: $NOTIFICATION_SERVICE_TOKEN"
echo ""

# Function to extract ID from response
get_id() {
    echo "$1" | grep -o '"id" *: *[0-9][0-9]*' | sed 's/.*: *//'
}

# 1. Syndicate Invitation
echo "--- Creating Template: Syndicate Invitation ---"
RESPONSE=$(curl -s -X 'POST' \
  "$NOTIFICATION_SERVICE_URL/api/v1/templates" \
  -H 'accept: application/json' \
  -H "X-Service-Token: $NOTIFICATION_SERVICE_TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
  "fromEmail": "'"$FROM_EMAIL"'",
  "name": "Syndicate Invitation",
  "description": "Template for Syndicate Invitation",
  "subject": "You'\''re invited to join {{syndicateName}} on Ugate!",
  "bodyHtml": "<div style=\"font-family: '\''Helvetica Neue'\'', Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;\"><div style=\"background-color: #0056b3; padding: 20px; text-align: center;\"><h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Ugate</h1></div><div style=\"padding: 30px; color: #333333; line-height: 1.6;\"><h2 style=\"color: #0056b3; margin-top: 0;\">Invitation to Join</h2><p>Hello <strong>{{firstName}}</strong>,</p><p>You have been invited to join the syndicate <strong>{{syndicateName}}</strong> on Ugate.</p><p>Please log in to accept the invitation and access your dashboard.</p><div style=\"text-align: center; margin-top: 30px; margin-bottom: 30px;\"><a href=\"{{loginUrl}}\" style=\"background-color: #0056b3; color: #ffffff; padding: 12px 24px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">Login to Accept</a></div><p>Best regards,<br>The Ugate Team</p></div><div style=\"background-color: #f8f9fa; padding: 15px; text-align: center; font-size: 12px; color: #888888;\">&copy; 2026 Ugate. All rights reserved.</div></div>",
  "type": "EMAIL"
}')
ID=$(get_id "$RESPONSE")
echo "application.external.notification-invite-template-id : $ID"
echo ""

# 2. New Event Alert
echo "--- Creating Template: New Event Alert ---"
RESPONSE=$(curl -s -X 'POST' \
  "$NOTIFICATION_SERVICE_URL/api/v1/templates" \
  -H 'accept: application/json' \
  -H "X-Service-Token: $NOTIFICATION_SERVICE_TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
  "fromEmail": "'"$FROM_EMAIL"'",
  "name": "New Event Alert",
  "description": "Template for New Event Alert",
  "subject": "📢 New Event: {{eventName}} on Ugate!",
  "bodyHtml": "<div style=\"font-family: '\''Helvetica Neue'\'', Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;\"><div style=\"background-color: #0056b3; padding: 20px; text-align: center;\"><h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Ugate</h1></div><div style=\"padding: 30px; color: #333333; line-height: 1.6;\"><h2 style=\"color: #0056b3; margin-top: 0;\">New Event Announced!</h2><p>Hello,</p><p>A new and exciting event has just been published: <strong>{{eventName}}</strong>.</p><p>Don'\''t miss out on this opportunity!</p><div style=\"text-align: center; margin-top: 30px; margin-bottom: 30px;\"><a href=\"{{eventUrl}}\" style=\"background-color: #0056b3; color: #ffffff; padding: 12px 24px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">View Event Details</a></div><p>Best regards,<br>The Ugate Team</p></div><div style=\"background-color: #f8f9fa; padding: 15px; text-align: center; font-size: 12px; color: #888888;\">&copy; 2026 Ugate. All rights reserved.</div></div>",
  "type": "EMAIL"
}')
ID=$(get_id "$RESPONSE")
echo "application.external.notification-new-event-alert-templatet-id : $ID"
echo ""

# 3. Publication Comment Alert
echo "--- Creating Template: Publication Comment Alert ---"
RESPONSE=$(curl -s -X 'POST' \
  "$NOTIFICATION_SERVICE_URL/api/v1/templates" \
  -H 'accept: application/json' \
  -H "X-Service-Token: $NOTIFICATION_SERVICE_TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
  "fromEmail": "'"$FROM_EMAIL"'",
  "name": "Publication Comment Alert",
  "description": "Template for Publication Comment Alert",
  "subject": "💬 New Comment on your Post: \"{{publicationTitle}}\"",
  "bodyHtml": "<div style=\"font-family: '\''Helvetica Neue'\'', Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;\"><div style=\"background-color: #0056b3; padding: 20px; text-align: center;\"><h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Ugate</h1></div><div style=\"padding: 30px; color: #333333; line-height: 1.6;\"><h2 style=\"color: #0056b3; margin-top: 0;\">New Comment</h2><p>Hello,</p><p><strong>{{firstName}}</strong> has just commented on your publication: <em>\"{{publicationTitle}}\"</em>.</p><p>Join the conversation now!</p><div style=\"text-align: center; margin-top: 30px; margin-bottom: 30px;\"><a href=\"{{publicationUrl}}\" style=\"background-color: #17a2b8; color: #ffffff; padding: 12px 24px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">View Comment</a></div><p>Best regards,<br>The Ugate Team</p></div><div style=\"background-color: #f8f9fa; padding: 15px; text-align: center; font-size: 12px; color: #888888;\">&copy; 2026 Ugate. All rights reserved.</div></div>",
  "type": "EMAIL"
}')
ID=$(get_id "$RESPONSE")
echo "application.external.notification-publication-comment-alert-template-id : $ID"
echo ""

# 4. Publication Reaction Alert
echo "--- Creating Template: Publication Reaction Alert ---"
RESPONSE=$(curl -s -X 'POST' \
  "$NOTIFICATION_SERVICE_URL/api/v1/templates" \
  -H 'accept: application/json' \
  -H "X-Service-Token: $NOTIFICATION_SERVICE_TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
  "fromEmail": "'"$FROM_EMAIL"'",
  "name": "Publication Reaction Alert",
  "description": "Template for Publication Reaction Alert",
  "subject": "👍 New Reaction on your Post: \"{{publicationTitle}}\"",
  "bodyHtml": "<div style=\"font-family: '\''Helvetica Neue'\'', Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;\"><div style=\"background-color: #0056b3; padding: 20px; text-align: center;\"><h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Ugate</h1></div><div style=\"padding: 30px; color: #333333; line-height: 1.6;\"><h2 style=\"color: #0056b3; margin-top: 0;\">New Reaction</h2><p>Hello,</p><p><strong>{{firstName}}</strong> reacted to your publication: <em>\"{{publicationTitle}}\"</em>.</p><p>See who is interacting with your content.</p><div style=\"text-align: center; margin-top: 30px; margin-bottom: 30px;\"><a href=\"{{publicationUrl}}\" style=\"background-color: #ffc107; color: #333333; padding: 12px 24px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">View Post</a></div><p>Best regards,<br>The Ugate Team</p></div><div style=\"background-color: #f8f9fa; padding: 15px; text-align: center; font-size: 12px; color: #888888;\">&copy; 2026 Ugate. All rights reserved.</div></div>",
  "type": "EMAIL"
}')
ID=$(get_id "$RESPONSE")
echo "application.external.notification-publication-react-alert-template-id : $ID"
echo ""

# 5. Admin New Publication Alert
echo "--- Creating Template: Admin New Publication Alert ---"
RESPONSE=$(curl -s -X 'POST' \
  "$NOTIFICATION_SERVICE_URL/api/v1/templates" \
  -H 'accept: application/json' \
  -H "X-Service-Token: $NOTIFICATION_SERVICE_TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
  "fromEmail": "'"$FROM_EMAIL"'",
  "name": "Admin New Publication Alert",
  "description": "Template for Admin New Publication Alert",
  "subject": "🔔 Admin Alert: New Publication in your Syndicate",
  "bodyHtml": "<div style=\"font-family: '\''Helvetica Neue'\'', Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;\"><div style=\"background-color: #dc3545; padding: 20px; text-align: center;\"><h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Admin Alert</h1></div><div style=\"padding: 30px; color: #333333; line-height: 1.6;\"><h2 style=\"color: #dc3545; margin-top: 0;\">New Publication Created</h2><p>Hello Admin,</p><p>A new publication titled <em>\"{{publicationTitle}}\"</em> has been created by <strong>{{authorName}}</strong> in your syndicate.</p><p>Please review the content to ensure it meets community guidelines.</p><div style=\"text-align: center; margin-top: 30px; margin-bottom: 30px;\"><a href=\"{{publicationUrl}}\" style=\"background-color: #dc3545; color: #ffffff; padding: 12px 24px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">Review Publication</a></div><p>Best regards,<br>The Ugate Team</p></div><div style=\"background-color: #f8f9fa; padding: 15px; text-align: center; font-size: 12px; color: #888888;\">&copy; 2026 Ugate. All rights reserved.</div></div>",
  "type": "EMAIL"
}')
ID=$(get_id "$RESPONSE")
echo "application.external.notification-admin-alert-when-new-publication-template-id : $ID"
echo ""

# 6. Admin Event Acceptance Alert
echo "--- Creating Template: Admin Event Acceptance Alert ---"
RESPONSE=$(curl -s -X 'POST' \
  "$NOTIFICATION_SERVICE_URL/api/v1/templates" \
  -H 'accept: application/json' \
  -H "X-Service-Token: $NOTIFICATION_SERVICE_TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
  "fromEmail": "'"$FROM_EMAIL"'",
  "name": "Admin Event Acceptance Alert",
  "description": "Template for Admin Event Acceptance Alert",
  "subject": "✅ Admin Alert: Event \"{{eventName}}\" Accepted",
  "bodyHtml": "<div style=\"font-family: '\''Helvetica Neue'\'', Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;\"><div style=\"background-color: #28a745; padding: 20px; text-align: center;\"><h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Admin Alert</h1></div><div style=\"padding: 30px; color: #333333; line-height: 1.6;\"><h2 style=\"color: #28a745; margin-top: 0;\">Event Accepted</h2><p>Hello Admin,</p><p>The event <em>\"{{eventName}}\"</em> organized by <strong>{{organizerName}}</strong> has been officially accepted.</p><p>It is now live and visible to users.</p><div style=\"text-align: center; margin-top: 30px; margin-bottom: 30px;\"><a href=\"{{eventUrl}}\" style=\"background-color: #28a745; color: #ffffff; padding: 12px 24px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">View Event</a></div><p>Best regards,<br>The Ugate Team</p></div><div style=\"background-color: #f8f9fa; padding: 15px; text-align: center; font-size: 12px; color: #888888;\">&copy; 2026 Ugate. All rights reserved.</div></div>",
  "type": "EMAIL"
}')
ID=$(get_id "$RESPONSE")
echo "application.external.notification-admin-alert-accept-event-template-id : $ID"
echo ""

echo "Script finished."