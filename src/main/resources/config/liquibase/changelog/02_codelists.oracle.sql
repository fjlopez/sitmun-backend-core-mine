--liquibase formatted sql
--changeset sitmun:2 dbms:oracle

-- STM_TERRITORY.TER_SCOPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (1, 'territory.scope', 'M', 0, 'Municipality');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (2, 'territory.scope', 'R', 0, 'Regional');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (3, 'territory.scope', 'T', 0, 'Total');

-- STM_USER.TER_IDENTTYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (4, 'user.identificationType', 'DNI', 0, 'DNI');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (5, 'user.identificationType', 'NIE', 0, 'NIE');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (6, 'user.identificationType', 'PAS', 0, 'Passport');

-- STM_GEOINFO.GEO_LEGENDTIP
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (7, 'cartography.legendType', 'LINK', 0, 'Static link');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (8, 'cartography.legendType', 'LEGENDGRAPHIC', 0, 'Get Legend Graphic');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (9, 'cartography.legendType', 'CAPABILITIES', 0, 'GetCapabilities');

-- STM_GEOINFO.GEO_GEOMTYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (10, 'cartography.geometryType', 'POINT', 0, 'Point');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (11, 'cartography.geometryType', 'LINE', 0, 'Line');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (12, 'cartography.geometryType', 'POLYGON', 0, 'Polygon');

-- STM_FIL_GI.FGI_TYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (13, 'cartographyFilter.type', 'C', 0, 'Custom');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (14, 'cartographyFilter.type', 'D', 0, 'Defined');

-- STM_FIL_GI.FGI_VALUETYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (15, 'cartographyFilter.valueType', 'A', 0, 'Alphanumeric');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (16, 'cartographyFilter.valueType', 'N', 0, 'Number');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (17, 'cartographyFilter.valueType', 'D', 0, 'Date');

-- STM_PAR_GI.PGI_FORMAT
-- TODO Validate value and description of code list cartographyParameter.format
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (18, 'cartographyParameter.format', 'I', 0, 'Image');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (19, 'cartographyParameter.format', 'N', 0, 'Number');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (20, 'cartographyParameter.format', 'P', 0, 'Percentage');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (21, 'cartographyParameter.format', 'T', 0, 'Text');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (22, 'cartographyParameter.format', 'U', 0, 'URL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (23, 'cartographyParameter.format', 'F', 0, 'Date');

-- STM_PAR_GI.PGI_TYPE
-- TODO Validate description of code list cartographyParameter.type
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (24, 'cartographyParameter.type', 'INFO', 0, 'INFO');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (25, 'cartographyParameter.type', 'SELECT', 0, 'SELECT');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (26, 'cartographyParameter.type', 'INFOSELECT', 0, 'INFOSELECT');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (27, 'cartographyParameter.type', 'FILTRO_INFO', 0, 'FILTRO_INFO');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (28, 'cartographyParameter.type', 'FILTRO_ESPACIAL', 0, 'FILTRO_SPACIAL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (29, 'cartographyParameter.type', 'FILTRO', 0, 'FILTRO');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (30, 'cartographyParameter.type', 'EDIT', 0, 'EDIT');

-- STM_GRP_GI.GGI_TYPE
-- TODO Validate value and description of code list cartographyPermission.type
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (31, 'cartographyPermission.type', 'C', 1, 'Cartography group');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (32, 'cartographyPermission.type', 'F', 1, 'Background map');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (33, 'cartographyPermission.type', 'M', 1, 'Location map');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (34, 'cartographyPermission.type', 'I', 1, 'Report');

-- STM_PAR_APP.PAP_TYPE
-- TODO Validate value and description of code list applicationParameter.type
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (35, 'applicationParameter.type', 'MOBILE', 0, 'Mobile app parameter');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (36, 'applicationParameter.type', 'Nomenclator', 0, 'Nomenclator parameter');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (37, 'applicationParameter.type', 'PRINT_TEMPLATE', 0, 'Print template');

-- STM_SERVICE.SER_PROTOCOL
-- TODO Validate value and description of code list service.type
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (38, 'service.type', 'AIMS', 0, 'AIMS');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (39, 'service.type', 'FME', 0, 'FME');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (40, 'service.type', 'TC', 0, 'TC');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (41, 'service.type', 'WFS', 0, 'WFS');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (42, 'service.type', 'WMS', 0, 'WMS');

-- STM_SERVICE.SER_NAT_PROT
-- TODO Provide value and description for code list service.nativeProtocol

-- STM_PAR_SER.PSE_TYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (43, 'serviceParameter.type', 'INFO', 0, 'GetFeatureInfo');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (44, 'serviceParameter.type', 'WMS', 0, 'GetMap');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (45, 'serviceParameter.type', 'OLPARAM', 0, 'OpenLayers');

-- STM_PAR_TSK.PTT_TYPE
-- TODO Validate description of code list taskParameter.type
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (46, 'taskParameter.type', 'CAMPO', 0, 'CAMPO');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (47, 'taskParameter.type', 'CAPA', 0, 'CAPA');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (48, 'taskParameter.type', 'EDIT', 0, 'EDIT');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (49, 'taskParameter.type', 'FILTRO', 0, 'FILTRO');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (50, 'taskParameter.type', 'FME', 0, 'FME');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (51, 'taskParameter.type', 'GEOM', 0, 'GEOM');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (52, 'taskParameter.type', 'LABEL', 0, 'LABEL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (53, 'taskParameter.type', 'RELM', 0, 'RELM');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (54, 'taskParameter.type', 'RELS', 0, 'RELS');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (55, 'taskParameter.type', 'SQL', 0, 'SQL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (56, 'taskParameter.type', 'TIPO', 0, 'TIPO');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (57, 'taskParameter.type', 'VISTA', 0, 'VISTA');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (87, 'taskParameter.type', 'DATAINPUT', 0, 'DATAINPUT');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (88, 'taskParameter.type', 'VALOR', 0, 'VALOR');

-- STM_PAR_TSK.PTT_FORMAT
-- TODO Validate description of code list taskParameter.format
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (58, 'taskParameter.format', 'T', 0, 'Text');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (59, 'taskParameter.format', 'F', 0, 'Date');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (60, 'taskParameter.format', 'N', 0, 'Number');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (61, 'taskParameter.format', 'L', 0, 'List (from a query)');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (62, 'taskParameter.format', 'U', 0, 'URL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (63, 'taskParameter.format', 'I', 0, 'Image');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (64, 'taskParameter.format', 'C', 0, 'Email');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (65, 'taskParameter.format', 'R', 0, 'Relation attribute between tables');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (66, 'taskParameter.format', 'S', 0, 'Select for assigning a value');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (67, 'taskParameter.format', 'B', 0, 'Database (trigger)');

-- STM_DOWNLOAD.DOW_EXT
-- TODO Provide value and description for code list downloadTask.format

-- STM_DOWNLOAD.DOW_TYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (68, 'downloadTask.scope', 'U', 0, 'Isolated');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (69, 'downloadTask.scope', 'A', 0, 'Application');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (70, 'downloadTask.scope', 'C', 0, 'Layer');

-- STM_QUERY.QUE_TYPE
-- TODO Validate description of code list queryTask.scope
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (71, 'queryTask.scope', 'URL', 0, 'URL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (72, 'queryTask.scope', 'SQL', 0, 'SQL');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (73, 'queryTask.scope', 'WS', 0, 'WS');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (74, 'queryTask.scope', 'INFORME', 0, 'INFORME');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (75, 'queryTask.scope', 'TAREA', 0, 'TAREA');

-- STM_POST.POS_TYPE
-- TODO Validate description of code list userPosition.type
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (76, 'userPosition.type', 'RE', 0, 'RE');

-- STM_THEMATIC.THE_RANKTYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (77, 'thematicMap.type', 'VU', 0, 'Unique values');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (78, 'thematicMap.type', 'RE', 0, 'Equal record count');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (79, 'thematicMap.type', 'RL', 0, 'Equal interval size');

-- STM_THEMATIC.THE_VALUETYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (80, 'thematicMap.valueType', 'STR', 0, 'String');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (81, 'thematicMap.valueType', 'DOU', 0, 'Double');

-- STM_THEMATIC.THE_DESTINATION
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (82, 'thematicMap.destination', 'WS', 0, 'Web Service');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (83, 'thematicMap.destination', 'WS_HERMES', 0, 'Hermes Web Service');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (84, 'thematicMap.destination', 'UPLOADED', 0, 'Uploaded JSON file');

-- STM_THE_RANK.TRK_STYLEINT
-- STM_THE_RANK.TRK_STYLE
-- TODO Provide value and description for code list thematicMapRange.style

-- STM_APPLICATION.APP_TYPE
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (85, 'application.type', 'I', 0, 'Internal');
INSERT INTO STM_CODELIST(COD_ID, COD_LIST, COD_VALUE, COD_SYSTEM, COD_DESCRIPTION)
VALUES (86, 'application.type', 'E', 0, 'External');

INSERT INTO STM_SEQUENCE(SEQ_NAME, SEQ_COUNT)
VALUES ('COD_ID', 88);