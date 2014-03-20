import socket
import subprocess

sock=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
sock.bind(('192.168.1.55',5565))
def getVolume():
	#print subprocess.Popen("osascript -e 'output volume of (get volume settings)'",shell=True,stdout=subprocess.PIPE).communicate()[0]
	print int(subprocess.Popen("osascript -e 'output volume of (get volume settings)'",shell=True,stdout=subprocess.PIPE).communicate()[0])
	return int(subprocess.Popen("osascript -e 'output volume of (get volume settings)'",shell=True,stdout=subprocess.PIPE).communicate()[0])/8
while True:
	print str(getVolume()-5)
	data=sock.recvfrom(1024)
	print data[0]
	if (data[0]=='up' ):
		#print str(getVolume())
		print getVolume()
		print "osascript -e 'set volume "+str(getVolume()+1)+"'"
		if (getVolume()+1>7):
			subprocess.Popen("osascript -e 'set volume "+str(getVolume()+2)+"'",shell=True)
	elif (data[0]=='down'):
		print getVolume()
		print "osascript -e 'set volume "+str(getVolume()-1)+"'"
		subprocess.Popen("osascript -e 'set volume "+str(getVolume()-1)+"'",shell=True)
	elif (data[0]=='mute'):
		subprocess.Popen("osascript -e 'set volume 0'",shell=True)