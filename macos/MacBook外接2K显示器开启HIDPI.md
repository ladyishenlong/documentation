#System Integrity Protection SIP
- 重启MacBook并且长按command+R进入恢复模式，在实用工具中打开终端输入命令关闭SIP
```
csrutil disable
```
- 在开启hidpi之后重新打开sip
```
csrutil enable
```
# 开启hidpi
- 输入命令
```
sudo defaults write /Library/Preferences/com.apple.windowserver DisplayResolutionEnabled -bool YES
```
- 使用如下命令 获取显示器的DisplayVendorID和DisplayProductID；在连接显示器的情况下会出现两组值，可以先将连接线拔掉，确定哪组是macbook本身显示器的id
```
ioreg -l | grep "DisplayVendorID"

ioreg -l | grep "DisplayProductID"
```
- 创建文件夹DisplayVendorID-？，其中？是显示器的DisplayVendorID的十六进制的小写
- 创建文件DisplayProductID-？其中？是显示器的DisplayProductID的十六进制小写，注意该文件没有后缀名
- 提供DisplayProductID-？文件的内容，里面已经有了1080hidpi，在DisplayProductID和DisplayVendorID对应的？处分别填入该显示器十进制的值保存即可
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
	<key>DisplayProductID</key>
	<integer>  ?  </integer>
	<key>DisplayVendorID</key>
	<integer>  ? </integer>
	<key>scale-resolutions</key>
	<array>
		<data>
		AAAKAAAABaAAAAABACAAAA==
		</data>
		<data>
		AAAFAAAAAtAAAAABACAAAA==
		</data>
		<data>
		AAAPAAAACHAAAAABACAAAA==
		</data>
		<data>
		AAAHgAAABDgAAAABACAAAA==
		</data>
		<data>
		AAAMgAAABwgAAAABACAAAA==
		</data>
		<data>
		AAAGQAAAA4QAAAABACAAAA==
		</data>
		<data>
		AAAKAgAABaAAAAABACAAAA==
		</data>
		<data>
		AAAKrAAABgAAAAABACAAAA==
		</data>
		<data>
		AAAFVgAAAwAAAAABACAAAA==
		</data>
	</array>
</dict>
</plist>

```
- 然后把整个文件夹拷贝到/System/Library/Displays/Contents/Resources/Overrides/即可
- 最后使用rdm这样的软件对显示器分辨率进行切换
#无法写入
- 在Mac OS 10.15之后的系统里，无法对/System/Library/Displays/Contents/Resources/Overrides/路径的文件进行写入，可以执行以下命令解决
```
sudo mount -uw /

killall Finder
```

