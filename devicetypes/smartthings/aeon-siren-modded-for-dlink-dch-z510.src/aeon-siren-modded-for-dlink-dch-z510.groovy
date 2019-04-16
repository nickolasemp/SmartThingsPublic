/**
 *  Copyright 2015 SmartThings, Modded by Knackrack615
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Aeon Siren
 *
 *  Author: SmartThings
 *  Date: 2014-07-15
 */
 
preferences {
   // PROB-1673 Since there is a bug with how defaultValue and range are handled together, we won't rely on defaultValue and won't set required, but will use the default values in the code below when needed.
   input "defaultSound", "enum", title: "Default Sound to use for the Siren?", options: ["Emergency","FireAlarm","Ambulance","PoliceCar","DoorChime","BeepCode"], required: false, defaultValue: "DoorChime"
   //input "sound", "number", title: "Siren sound (1-5)", range: "1..5" //, defaultValue: 1, required: true//, displayDuringSetup: true  // don't display during setup until defaultValue is shown
   input "volume", "number", title: "Volume (1-3)", range: "1..3" //, defaultValue: 3, required: true//, displayDuringSetup: true
}
    
metadata {
 definition (name: "Aeon Siren (Modded for Dlink DCH-Z510)", namespace: "smartthings", author: "SmartThings", ocfDeviceType: "x.com.st.d.siren") {
    capability "Actuator"
    capability "Alarm"
    capability "Switch"
    capability "Health Check"
    capability "Configuration"
 
    command "FireAlarm"
    command "Ambulance"
    command "PoliceCar"
    command "DoorChime"
    command "BeepCode"
    command "Emergency"
 
    fingerprint deviceId: "0x1005", inClusters: "0x25, 0x59, 0x5A, 0x5E, 0x70, 0x71, 0x72, 0x73, 0x80, 0x85, 0x86, 0x87", deviceJoinName: "Aeotec Siren (Gen 5)"
    fingerprint deviceId: "0x1005", inClusters: "0x25, 0x59, 0x5A, 0x5E, 0x70, 0x71, 0x72, 0x73, 0x80, 0x85, 0x86, 0x87, 0x98", deviceJoinName: "Aeotec Siren (Gen 5).4"
    //0x20,0x25,0x30,0x59,0x5A,0x5E,0x70,0x72,0x73,0x7A,0x85,0x86,0x98
  }
 
 
 simulator {
 
 }
 
 /*How to show as a device and its tiles, https://docs.smartthings.com/en/latest/device-type-developers-guide/tiles-metadata.html*/
 tiles(scale: 2) {
    multiAttributeTile(name:"alarm", type: "generic", width: 6, height: 4){
        tileAttribute ("device.alarm", key: "PRIMARY_CONTROL") {
            attributeState "off", label:'off', action:'alarm.siren', icon:"st.alarm.alarm.alarm", backgroundColor:"#ffffff"
            attributeState "both", label:'alarm!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
        }
	}
    
    standardTile("FireAlarm", "device.button", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Fire Alarm', action:"FireAlarm", icon:"st.Outdoor.outdoor10"
    }
    standardTile("Ambulance", "device.button", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Ambulance', action:"Ambulance", icon:"st.Transportation.transportation2"
    }
    standardTile("PoliceCar", "device.button", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Police Car', action:"PoliceCar", icon:"st.Transportation.transportation8"
    }
    standardTile("DoorChime", "device.button", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Door Chime', action:"DoorChime", icon:"st.Home.home30"
    }
    standardTile("BeepCode", "device.button", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Beep Code', action:"BeepCode", icon:"st.Seasonal Winter.seasonal-winter-002"
    }
    standardTile("Emergency", "device.button", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Beep Code', action:"Emergency", icon:"st.alarm.alarm.alarm"
    }
    standardTile("off", "device.alarm", inactiveLabel: true, decoration: "flat", width: 2, height: 2) {
        state "default", label:'Off', action:"alarm.off", icon:"st.thermostat.heating-cooling-off"
    }
  
    main (["alarm"])
    details(["alarm", "FireAlarm", "Ambulance", "PoliceCar", "DoorChime", "BeepCode", "Emergency", "off"])
 }
}



/*
metadata {
	// Automatically generated. Make future change here.
	definition (name: "SimpliSafe", namespace: "tobycth3", author: "Toby Harris") {
		capability "Alarm"
		capability "Polling"
		capability "Acceleration Sensor"
        capability "Contact Sensor"
		capability "Carbon Monoxide Detector"
        capability "Lock"
		capability "Presence Sensor"
		capability "Smoke Detector"
        capability "Temperature Measurement"
        capability "Water Sensor"
		command "home"
		command "away"
		command "off"
		command "update_state"
		command "update_temp"
		attribute "events", "string"
		attribute "recent_alarm", "string" 
		attribute "recent_fire", "string" 
		attribute "recent_co", "string" 
		attribute "recent_flood", "string" 
		attribute "warnings", "string"        
	}

	simulator {
		// TODO: define status and reply messages here
	}


tiles(scale: 2) {
    multiAttributeTile(name:"alarm", type: "generic", width: 6, height: 4){
        tileAttribute ("device.alarm", key: "PRIMARY_CONTROL") {
            attributeState "off", label:'${name}', icon: "st.security.alarm.off", backgroundColor: "#505050"
            attributeState "home", label:'${name}', icon: "st.Home.home4", backgroundColor: "#00BEAC"
            attributeState "away", label:'${name}', icon: "st.security.alarm.on", backgroundColor: "#008CC1"
			attributeState "pending off", label:'${name}', icon: "st.security.alarm.off", backgroundColor: "#ffffff"
			attributeState "pending away", label:'${name}', icon: "st.Home.home4", backgroundColor: "#ffffff"
			attributeState "pending home", label:'${name}', icon: "st.security.alarm.on", backgroundColor: "#ffffff"
			attributeState "failed set", label:'error', icon: "st.secondary.refresh", backgroundColor: "#d44556"
        }
		
		tileAttribute("device.events", key: "SECONDARY_CONTROL", wordWrap: true) {
			attributeState("default", label:'${currentValue}')
		}
    }	
	
    standardTile("off", "device.alarm", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
        state ("off", label:"off", action:"off", icon: "st.security.alarm.off", backgroundColor: "#008CC1", nextState: "pending")
        state ("away", label:"off", action:"off", icon: "st.security.alarm.off", backgroundColor: "#505050", nextState: "pending")
        state ("home", label:"off", action:"off", icon: "st.security.alarm.off", backgroundColor: "#505050", nextState: "pending")
        state ("pending", label:"pending", icon: "st.security.alarm.off", backgroundColor: "#ffffff")
	}
	
    standardTile("away", "device.alarm", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
        state ("off", label:"away", action:"away", icon: "st.security.alarm.on", backgroundColor: "#505050", nextState: "pending") 
		state ("away", label:"away", action:"away", icon: "st.security.alarm.on", backgroundColor: "#008CC1", nextState: "pending")
        state ("home", label:"away", action:"away", icon: "st.security.alarm.on", backgroundColor: "#505050", nextState: "pending")
		state ("pending", label:"pending", icon: "st.security.alarm.on", backgroundColor: "#ffffff")
	}
	
    standardTile("home", "device.alarm", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
        state ("off", label:"home", action:"home", icon: "st.Home.home4", backgroundColor: "#505050", nextState: "pending")
        state ("away", label:"home", action:"home", icon: "st.Home.home4", backgroundColor: "#505050", nextState: "pending")
		state ("home", label:"home", action:"home", icon: "st.Home.home4", backgroundColor: "#008CC1", nextState: "pending")
		state ("pending", label:"pending", icon: "st.Home.home4", backgroundColor: "#ffffff")
	}
    
		standardTile("recent_alarm", "device.contact", inactiveLabel: false, width: 2, height: 2) {
			state "closed", label:'Alarm', icon: "st.security.alarm.clear", backgroundColor: "#50C65F"
			state "open", label:'ALARM', icon: "st.security.alarm.alarm", backgroundColor: "#d44556"
		}
		standardTile("freeze", "device.freeze_status", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state ("no alert", label:'Temp', action:"update_temp", icon: "st.alarm.temperature.normal", backgroundColor: "#50C65F", nextState: "updating")
			state ("alert", label:'TEMP', action:"update_temp", icon: "st.alarm.temperature.freeze", backgroundColor: "#d44556", nextState: "updating")
			state ("updating", label:"updating", icon: "st.alarm.temperature.normal", backgroundColor: "#ffffff")
		}
		standardTile("recent_fire", "device.smoke", inactiveLabel: false, width: 2, height: 2) {
			state "clear", label:'Fire', icon: "st.alarm.smoke.clear", backgroundColor: "#50C65F"
			state "detected", label:'FIRE', icon: "st.alarm.smoke.smoke", backgroundColor: "#d44556"
		}
		standardTile("recent_co", "device.carbonMonoxide", inactiveLabel: false, width: 2, height: 2) {
			state "clear", label:'CO2', icon: "st.alarm.carbon-monoxide.clear", backgroundColor: "#50C65F"
			state "detected", label:'CO2', icon: "st.alarm.carbon-monoxide.carbon-monoxide", backgroundColor: "#d44556"
		}
		standardTile("recent_flood", "device.water", inactiveLabel: false, width: 2, height: 2) {
			state "dry", label:'Flood', icon: "st.alarm.water.dry", backgroundColor: "#50C65F"
			state "wet", label:'FLOOD', icon: "st.alarm.water.wet", backgroundColor: "#d44556"
		}
		standardTile("warnings", "device.acceleration", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state ("inactive", label:'Base', action:"update_state", icon: "st.Kids.kids15", backgroundColor: "#50C65F", nextState: "updating")
			state ("active", label:'BASE', action:"update_state", icon: "st.Kids.kids15", backgroundColor: "#d44556", nextState: "updating")
			state ("updating", label:"updating", icon: "st.Kids.kids15", backgroundColor: "#ffffff")
		}
		standardTile("refresh", "device.alarm", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", action:"polling.poll", icon:"st.secondary.refresh"
		}

		main(["alarm"])
		details(["alarm","off", "away", "home", "recent_alarm", "freeze", "recent_fire", "recent_co", "recent_flood", "warnings", "refresh"])
	}
}
*/


 
def installed() {
// Device-Watch simply pings if no device events received for 30min(checkInterval)
    sendEvent(name: "checkInterval", value: 2 * 15 * 60 , displayed: false, data: [protocol: "zwave", hubHardwareId: device.hub.hardwareID, offlinePingable: "1"])
 
    // Get default values and set device to send us an update when alarm state changes from device
    response([secure(zwave.basicV1.basicGet()), secure(zwave.configurationV1.configurationSet(parameterNumber: 80, size: 1, configurationValue: [2]))])
}
 
def updated() {
    def commands = []
// Device-Watch simply pings if no device events received for 30min(checkInterval)
    sendEvent(name: "checkInterval", value: 2 * 15 * 60, displayed: false, data: [protocol: "zwave", hubHardwareId: device.hub.hardwareID, offlinePingable: "1"])
 
    if(!state.sound) state.sound = 1
    if(!state.volume) state.volume = 3
 
    log.debug "settings: ${settings.inspect()}, state: ${state.inspect()}"
 
    Short sound = (settings.sound as Short) ?: 1
    Short volume = (settings.volume as Short) ?: 3
 
    if (sound != state.sound || volume != state.volume) {
        state.sound = sound
        state.volume = volume
        commands << secure(zwave.configurationV1.configurationSet(parameterNumber: 37, size: 2, configurationValue: [sound, volume]))
        commands << "delay 1000"
        commands << secure(zwave.basicV1.basicSet(value: 0x00))
    }
 
    // Set device to send us an update when alarm state changes from device
    commands << secure(zwave.configurationV1.configurationSet(parameterNumber: 80, size: 1, configurationValue: [2]))
 
    response(commands)
}
 
def parse(String description) {
    log.debug "parse($description)"
    def result = null
    if (description.startsWith("Err")) {
        if (state.sec) {
            result = createEvent(descriptionText:description, displayed:false)
        } else {
            result = createEvent(
                    descriptionText: "This device failed to complete the network security key exchange. If you are unable to control it via SmartThings, you must remove it from your network and add it again.",
                    eventType: "ALERT",
                    name: "secureInclusion",
                    value: "failed",
                    displayed: true,
            )
        }
    } else {
        def cmd = zwave.parse(description, [0x20: 1, 0x25: 1, 0x26: 1, 0x70: 1, 0x80: 1, 0x98: 1])
        if (cmd) {
            result = zwaveEvent(cmd)
            log.debug "Parse returned ${result?.inspect()}"
        } else {
			log.debug("Couldn't zwave.parse '$description'")
			null
        }
    }
    log.debug "Parse returned ${result?.inspect()}"
    return result
}
 
def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityCommandsSupportedReport cmd) {
	response(configure())
}

def configure() {
	log.debug "Resetting Siren Parameters to SmartThings Compatible Defaults"
	def cmds = []
    
	cmds << zwave.configurationV1.configurationSet(configurationValue: [0], parameterNumber: 4, size: 1)
    cmds << zwave.configurationV1.configurationSet(configurationValue: [0], parameterNumber: 29, size: 1)
    cmds << zwave.configurationV1.configurationSet(configurationValue: [6], parameterNumber: 31, size: 1)
    
    delayBetween(cmds, 500)
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
    log.debug "---CONFIGURATION REPORT V2--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd)
{
	[name: "switch", value: cmd.value ? "on" : "off", type: "digital", displayed: true, isStateChange: true]
}

def zwaveEvent(physicalgraph.zwave.commands.sensorbinaryv2.SensorBinaryReport cmd) {
	createEvent(name:"Alarm", cmd.sensorValue ? "on" : "off")
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
    log.debug "rx $cmd"
    [
        createEvent([name: "switch", value: cmd.value ? "on" : "off", displayed: false]),
        createEvent([name: "alarm", value: cmd.value ? "both" : "off"])
    ]
}
 
//def zwaveEvent(physicalgraph.zwave.Command cmd) {
//    createEvent(displayed: false, descriptionText: "$device.displayName: $cmd")
//}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	log.debug "Unhandled: $cmd"
    createEvent(descriptionText: cmd.toString(), isStateChange: false)
}

def zwaveEvent(physicalgraph.zwave.commands.notificationv3.NotificationReport cmd) {
if (cmd.notificationType == 6 && cmd.event == 22) {
		log.debug "Playing Door Chime"
	} else if (cmd.notificationType == 10 && cmd.event == 1) {
		log.debug "Playing Police Car"
	} else if (cmd.notificationType == 10 && cmd.event == 3) {
    	log.debug "Playing Ambulance"
    } else if (cmd.notificationType == 10 && cmd.event == 2) {
    	log.debug "Playing Fire Alarm"
    } else if (cmd.notificationType == 7 && cmd.event == 1) {
    	log.debug "Playing Emergency"
    }
}
 
def on() {
    log.debug "sending on"
    [
        secure(zwave.basicV1.basicSet(value: 0xFF)),
        secure(zwave.basicV1.basicGet())
    ]
}
 
def off() {
    log.debug "sending off"
    [
        secure(zwave.basicV1.basicSet(value: 0x00)),
        secure(zwave.basicV1.basicGet())
    ]
}
 
def strobe() {
    on()
}
 
def siren() {
    on()
}
 
def both() {
    on()
}

def Emergency() {
	log.debug "Sounding Siren With Emergency"
	[
		secure(zwave.notificationV3.notificationReport(event: 0x01, notificationType: 0x07)),
		secure(zwave.basicV1.basicGet())
	]
}

def FireAlarm() {
	log.debug "Sounding Siren With Fire Alarm"
	[
		secure(zwave.notificationV3.notificationReport(event: 0x02, notificationType: 0x0A)),
        secure(zwave.basicV1.basicGet())
	]
}

def Ambulance() {
	log.debug "Sounding Siren With Ambulance"
	[
		secure(zwave.notificationV3.notificationReport(event: 0x03, notificationType: 0x0A)),
        secure(zwave.basicV1.basicGet())
	]
}

def PoliceCar() {
	log.debug "Sounding Siren With Police Car"
	[
		secure(zwave.notificationV3.notificationReport(event: 0x01, notificationType: 0x0A)),
        secure(zwave.basicV1.basicGet())
	]
}

def DoorChime() {
	log.debug "Sounding Siren With Door Chime"
	[
		secure(zwave.notificationV3.notificationReport(event: 0x16, notificationType: 0x06)),
        secure(zwave.basicV1.basicGet())
	]
}

def BeepCode() {
    log.debug "Sounding Siren With Beep Code"
    [
        secure(zwave.notificationV3.notificationReport(event: 0x16, notificationType: 0x06)),
        "delay 600",
        secure(zwave.basicV1.basicSet(value: 0x00)),
        secure(zwave.notificationV3.notificationReport(event: 0x16, notificationType: 0x06)),
        "delay 600",
        secure(zwave.basicV1.basicSet(value: 0x00)),
        secure(zwave.notificationV3.notificationReport(event: 0x16, notificationType: 0x06)),
        "delay 600",
        secure(zwave.basicV1.basicSet(value: 0x00)),
        secure(zwave.basicV1.basicGet())
    ]
}

//https://graph.api.smartthings.com/ide/doc/zwave-utils.html#securityV1
private secure(physicalgraph.zwave.Command cmd) {
	if (state.sec) {
         log.debug "Sending Secure Command $cmd"
		zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	} else {
    	log.debug "Sending Insecure Command $cmd"
		cmd.format()
	}
}

private command(physicalgraph.zwave.Command cmd) {
	if ((zwaveInfo.zw == null && state.sec != 0) || zwaveInfo?.zw?.contains("s")) {
		 log.debug "Sending Secure Command $cmd"
         zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	} else {
		 log.debug "Sending Secure Command $cmd"
         cmd.format()
	}
}

/*def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand()
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	} else {
		log.warn "Unable to extract encapsulated cmd from $cmd"
		createEvent(descriptionText: cmd.toString())
	}
}*/

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x20: 1, 0x25: 3, 0x26: 3, 0x70: 1, 0x80: 1, 0x98: 1])
	state.sec = 1
	log.debug "encapsulated: ${encapsulatedCommand}"
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	} else {
		log.warn "Unable to extract encapsulated cmd from $cmd"
		createEvent(descriptionText: cmd.toString())
	}
}
 
/**
 * PING is used by Device-Watch in attempt to reach the Device
 * */
def ping() {
    secure(zwave.basicV1.basicGet())
}