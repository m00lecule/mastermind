import smtplib
import json

with open('./credentials.json', "r") as handler:
    info = json.load(handler)

user = info['user']
FROM = user
pwd = info['password']
SUBJECT = 'witojcie'
TEXT = 'witojcie'

# Prepare actual message
message = """From: %s\nTo: %s\nSubject: %s\n\n%s
""" % (FROM, ", ".join(FROM), SUBJECT, TEXT)
try:
    server = smtplib.SMTP("smtp.gmail.com", 587)
    server.ehlo()
    server.starttls()
    server.login(user, pwd)
    server.sendmail(FROM, FROM, message)
    server.close()
    print 'successfully sent the mail'
except:
    print "failed to send mail"