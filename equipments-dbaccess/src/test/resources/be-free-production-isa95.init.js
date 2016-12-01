use be-free-production-isa95
db.createUser({
	user : "bfp",
	pwd : "bfp",
	roles : [ {
		role : "readWrite",
		db : "be-free-production-isa95"
	} ]
});

/*---------------------------*/
/* EQUIPMENT COLLECTION INIT */
/*---------------------------*/

db.equipment.insert([ {
	"_id" : "E001",
	"description" : "Enterprise gmbh",
	"equipmentLevel" : "0",
	"hierarchyScope" : "Enterprise",
	"location" : "Worldwide",
	"equipmentClassIDs" : [],
	"mainAddress" : "Main Street, 12, 36777, Montevideo",
	"mainAddress_description" : "Administrative address of the company",
	"contacts" : "info@enterprise.com",
	"contacts_description" : "The contacts of the company"
}, {
	"_id" : "A001",
	"description" : "Area production Singapore",
	"equipmentLevel" : "1",
	"hierarchyScope" : "Area",
	"parent" : "E001",
	"location" : "Singapore",
	"equipmentClassIDs" : []
}, {
	"_id" : "0001",
	"description" : "Water Pump machine 01",
	"equipmentLevel" : "2",
	"hierarchyScope" : "WorkCell",
	"location" : "Area 1",
	"parent" : "A001",
	"equipmentClassIDs" : [ "Pump" ],
	"Pump_speed" : 14.50,
	"Pump_speed_description" : "Pump speed in m3/h",
	"Pump_serial" : "SN00110004454",
	"Pump_serial_description" : "Serial Number"
}, {
	"_id" : "0002",
	"description" : "Water Pump machine 02",
	"equipmentLevel" : "2",
	"hierarchyScope" : "WorkCell",
	"location" : "Area 1",
	"parent" : "A001",
	"equipmentClassIDs" : [ "Pump" ],
	"Pump_speed" : 18.00,
	"Pump_speed_description" : "Pump speed in m3/h",
	"Pump_serial" : "SN00110004455",
	"Pump_serial_description" : "Serial Number"
} ]);
