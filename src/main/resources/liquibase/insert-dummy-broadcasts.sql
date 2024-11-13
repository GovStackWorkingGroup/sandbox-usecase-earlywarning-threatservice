INSERT INTO broadcast (id, broadcastuuid, threat_id, countryId, countryName, title, status, notes, channelType, primaryLangMessage,
                       secondaryLangMessage, createdat, initiated, periodStart, periodEnd, createdBy)
VALUES (nextval('broadcast_id_seq'), '7a39af99-5ede-4cfd-a56a-c7737937d5a0', 1, 1, 'Kenya', 'Heavy Rain', 'PENDING', '', 'SMS', 'Flooding expected',
        'Lorem ipsum text', NOW(), NOW(), '2024-01-15', '2024-01-20','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'a8c3497b-85c5-43df-908f-32c6b312b02f', 2, 6, 'Ethiopia', 'Heatwave', 'SENT', '', 'SMS', 'Record high temps',
        'Lorem ipsum text', NOW(), NOW(), '2024-02-05', '2024-02-10', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'd83296ae-e6b8-45de-935c-413c2a3f5619', 3, 2, 'Somalia', 'Storm Surge', 'PENDING', '', 'SMS', 'Rising water levels',
        'Lorem ipsum text', NOW(), NOW(), '2024-03-10', '2024-03-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'b4768cb1-1c69-4bd4-9c91-c5b0f3b5bde7', 4, 3, 'Uganda', 'Flood Alert', 'SENT', '', 'SMS', 'Flooding risk high',
        'Lorem ipsum text', NOW(), NOW(), '2024-04-12', '2024-04-18', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'c3876c44-4f17-4046-8190-6845faff9087', 5, 4, 'South Sudan', 'Heat Index', 'PENDING', '', 'SMS',
        'Extremely hot conditions', 'Lorem ipsum text', NOW(), NOW(), '2024-05-03', '2024-05-08','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'a56b8d31-d477-4f7b-a561-b1a6ed232f8f', 6, 5, 'Djibouti', 'Tornado Watch', 'SENT', '', 'SMS', 'Tornadoes possible',
        'Lorem ipsum text', NOW(), NOW(), '2024-06-22', '2024-06-27', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'f479a05f-5f4c-4b9f-97a0-51ff7d7b9bba', 7, 1, 'Kenya', 'Wind Advisory', 'PENDING', '', 'SMS',
        'Gale force winds expected', 'Lorem ipsum text', NOW(), NOW(), '2024-07-14', '2024-07-19','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'b52fbc12-0300-44e1-b4ad-c25e318b074b', 8, 6, 'Ethiopia', 'Severe Storm', 'SENT', '', 'SMS',
        'Damaging winds possible', 'Lorem ipsum text', NOW(), NOW(), '2024-08-08', '2024-08-15', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'a8451b1d-29a7-4421-8364-35b1be49e87e', 9, 2, 'Somalia', 'Flash Flood', 'PENDING', '', 'SMS',
        'Heavy rains expected', 'Lorem ipsum text', NOW(), NOW(), '2024-09-10', '2024-09-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'b1a89f2e-94ff-48f0-9d12-2213b7a467b7', 10, 3, 'Uganda', 'Tropical Storm', 'SENT', '', 'SMS',
        'Rain and wind expected', 'Lorem ipsum text', NOW(), NOW(), '2024-10-01', '2024-10-07', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'd82c9778-6fe2-42a6-bb8b-bcf23065048d', 11, 4, 'South Sudan', 'Hailstorm', 'PENDING', '', 'SMS',
        'Hail to impact area', 'Lorem ipsum text', NOW(), NOW(), '2024-11-10', '2024-11-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'c73a6f79-cf56-4a66-9c74-e5cda387de57', 12, 5, 'Djibouti', 'Wind Chill', 'SENT', '', 'SMS', 'Cold winds arriving',
        'Lorem ipsum text', NOW(), NOW(), '2024-12-01', '2024-12-05','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '7b6e1e6e-cbd4-46ae-a9b5-13cb132f7adf', 13, 1, 'Kenya', 'Severe Heat', 'PENDING', '', 'SMS', 'Unbearable heat',
        'Lorem ipsum text', NOW(), NOW(), '2024-05-10', '2024-05-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'd62b436f-57b9-4668-bef3-67c0ebfe6f8b', 14, 6, 'Ethiopia', 'Flash Freeze', 'SENT', '', 'SMS',
        'Rapid temperature drop', 'Lorem ipsum text', NOW(), NOW(), '2024-06-03', '2024-06-07','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'f527f926-4605-488b-bb0b-e400b1060192', 15, 2, 'Somalia', 'Fire Risk', 'PENDING', '', 'SMS', 'Dry conditions',
        'Lorem ipsum text', NOW(), NOW(), '2024-07-12', '2024-07-17','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '2a635dbf-78d2-4a36-bce7-6c8ff509e4bb', 16, 3, 'Uganda', 'Lightning', 'SENT', '', 'SMS', 'Lightning strikes risk',
        'Lorem ipsum text', NOW(), NOW(), '2024-08-15', '2024-08-20','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'cb3c16b7-4d47-40c5-8a1f-9e049d99fc6b', 17, 4, 'South Sudan', 'Rainstorm', 'PENDING', '', 'SMS',
        'Heavy rains incoming', 'Lorem ipsum text', NOW(), NOW(), '2024-09-11', '2024-09-16','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '7321c857-16fc-48c9-bd79-b8b7b51c08ff', 18, 5, 'Djibouti', 'Heat Advisory', 'SENT', '', 'SMS',
        'Extreme temperatures', 'Lorem ipsum text', NOW(), NOW(), '2024-10-20', '2024-10-26','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '12d13b56-222d-4d5f-b903-23355d93d8fe', 19, 1, 'Kenya', 'Tropical Cyclone', 'PENDING', '', 'SMS',
        'Cyclone development', 'Lorem ipsum text', NOW(), NOW(), '2024-11-01', '2024-11-06', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '55909cc5-7b9c-4a97-92b4-9c87f306a800', 20, 6, 'Ethiopia', 'Dust Storm', 'SENT', '', 'SMS', 'Dust covering area',
        'Lorem ipsum text', NOW(), NOW(), '2024-12-10', '2024-12-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'bb8f94f9-5a98-4dbd-a614-251c44a8f1f2', 21, 2, 'Somalia', 'Storm Warning', 'PENDING', '', 'SMS',
        'Severe storm expected', 'Lorem ipsum text', NOW(), NOW(), '2024-01-22', '2024-01-28','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'e6a52eb2-2767-4a02-aba5-f756b7d1bb8d', 22, 3, 'Uganda', 'Dense Fog', 'SENT', '', 'SMS', 'Visibility reduced',
        'Lorem ipsum text', NOW(), NOW(), '2024-02-05', '2024-02-10', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'f7f4d964-61f3-4ae1-9a82-dfb3198bd5d7', 23, 4, 'South Sudan', 'Extreme Heat', 'PENDING', '', 'SMS',
        'Dangerously hot temps', 'Lorem ipsum text', NOW(), NOW(), '2024-03-12', '2024-03-17','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'd10d74ae-4f0e-4d42-993a-e5d9b4c8ae0f', 24, 5, 'Djibouti', 'Severe Winds', 'SENT', '', 'SMS',
        'High winds expected', 'Lorem ipsum text', NOW(), NOW(), '2024-04-01', '2024-04-05','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'f9d58a6e-6a59-44f5-b5c7-0ed62f10ebd6', 25, 1, 'Kenya', 'Drought Alert', 'PENDING', '', 'SMS', 'Water shortages',
        'Lorem ipsum text', NOW(), NOW(), '2024-05-20', '2024-05-25', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'c93bde3b-43a5-4b21-9b6a-72c053b91b34', 26, 6, 'Ethiopia', 'Coastal Flood', 'SENT', '', 'SMS', 'Rising tides',
        'Lorem ipsum text', NOW(), NOW(), '2024-06-08', '2024-06-12','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '0ac7f907-2f7b-4e3f-beb2-e7d27a11830e', 27, 2, 'Somalia', 'Thunderstorm', 'PENDING', '', 'SMS',
        'Intense thunderstorms', 'Lorem ipsum text', NOW(), NOW(), '2024-07-15', '2024-07-19', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'a2b2f3f8-5a3a-4dd8-8f04-0b2d41be11b7', 28, 3, 'Uganda', 'Blizzard', 'SENT', '', 'SMS', 'Heavy snowfall',
        'Lorem ipsum text', NOW(), NOW(), '2024-08-20', '2024-08-25', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '12a73c48-3a58-49b3-9c88-4858e6dc1f58', 29, 4, 'South Sudan', 'Extreme Cold', 'PENDING', '', 'SMS',
        'Sub-zero temperatures', 'Lorem ipsum text', NOW(), NOW(), '2024-09-01', '2024-09-05','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '4c9d9175-2827-4b6f-8b2a-e5d97df3e207', 30, 5, 'Djibouti', 'Cold Wave', 'SENT', '', 'SMS',
        'Rapid temperature drop', 'Lorem ipsum text', NOW(), NOW(), '2024-10-12', '2024-10-18', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '7bc8e8f3-9831-4cf6-8a87-7c88a6475f07', 31, 1, 'Kenya', 'Heavy Rainfall', 'PENDING', '', 'SMS',
        'Flooding potential', 'Lorem ipsum text', NOW(), NOW(), '2024-11-03', '2024-11-10','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'c59b6edc-2355-4508-896f-f3a50c4e02f9', 32, 6, 'Ethiopia', 'Freezing Rain', 'SENT', '', 'SMS',
        'Icy conditions forming', 'Lorem ipsum text', NOW(), NOW(), '2024-12-02', '2024-12-06', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'fa7d2316-0070-4039-9c66-87dfb71e6c94', 33, 2, 'Somalia', 'Extreme Winds', 'PENDING', '', 'SMS',
        'Severe wind conditions', 'Lorem ipsum text', NOW(), NOW(), '2024-05-14', '2024-05-20','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'e3a2b7db-89cf-4bc8-bdc5-9e4fb2e1d25e', 34, 3, 'Uganda', 'Ice Storm', 'SENT', '', 'SMS', 'Icy conditions expected',
        'Lorem ipsum text', NOW(), NOW(), '2024-06-01', '2024-06-07', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'aab47f6e-3abf-431e-bf93-58e8e8e10f87', 35, 4, 'South Sudan', 'Wildfire Risk', 'PENDING', '', 'SMS',
        'Fire danger high', 'Lorem ipsum text', NOW(), NOW(), '2024-07-05', '2024-07-10', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'a1e71aeb-5738-49d1-b911-b424ecb607f2', 36, 5, 'Djibouti', 'Flood Warning', 'SENT', '', 'SMS', 'Flooding imminent',
        'Lorem ipsum text', NOW(), NOW(), '2024-08-02', '2024-08-06','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'b4e6c8c2-836d-43ad-a1fb-96039e17944e', 37, 1, 'Kenya', 'Tornado Alert', 'PENDING', '', 'SMS',
        'Tornadoes expected', 'Lorem ipsum text', NOW(), NOW(), '2024-09-15', '2024-09-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '93f1e7f4-3e1f-4d7f-8bb4-7385fbd057fe', 38, 6, 'Ethiopia', 'Air Quality Alert', 'SENT', '', 'SMS',
        'Pollution levels high', 'Lorem ipsum text', NOW(), NOW(), '2024-10-01', '2024-10-05', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'f1d34a62-bc7d-4d7b-827f-4efb81d3f4ff', 39, 2, 'Somalia', 'Tropical Depression', 'PENDING', '', 'SMS',
        'Watch for development', 'Lorem ipsum text', NOW(), NOW(), '2024-11-10', '2024-11-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '0c7b7a62-d08b-43e6-8dc1-62f9d69d5a63', 40, 3, 'Uganda', 'Extreme Cold', 'SENT', '', 'SMS', 'Frigid conditions',
        'Lorem ipsum text', NOW(), NOW(), '2024-12-12', '2024-12-18','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '9d3c8eb1-6587-49b3-89b7-d0c70bfb2341', 1, 4, 'South Sudan', 'Landslide Alert', 'PENDING', '', 'SMS',
        'Unstable ground', 'Lorem ipsum text', NOW(), NOW(), '2024-01-12', '2024-01-18', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '2bfe87a2-4774-4cb1-bf85-16e02162e4e9', 2, 5, 'Djibouti', 'High Surf Warning', 'SENT', '', 'SMS',
        'Waves over 20 feet', 'Lorem ipsum text', NOW(), NOW(), '2024-02-04', '2024-02-08', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'b8f847f1-8731-46b7-8a10-6eb733c9bcfc', 3, 1, 'Kenya', 'Heat Warning', 'PENDING', '', 'SMS', 'Prolonged heatwave',
        'Lorem ipsum text', NOW(), NOW(), '2024-03-05', '2024-03-10','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'de8c4271-d87a-4c31-84bb-437b9e6cd7b1', 4, 6, 'Ethiopia', 'Hurricane Alert', 'SENT', '', 'SMS',
        'Hurricane approaching', 'Lorem ipsum text', NOW(), NOW(), '2024-04-07', '2024-04-13', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'c2e9f47b-4be5-4df6-9246-e8b8e7b4df0e', 5, 2, 'Somalia', 'Dense Smoke', 'PENDING', '', 'SMS',
        'Smoke affecting air', 'Lorem ipsum text', NOW(), NOW(), '2024-05-15', '2024-05-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '8b07b762-4185-4bbd-8827-f13af1b6e67a', 6, 3, 'Uganda', 'Extreme Rainfall', 'SENT', '', 'SMS',
        'Severe flooding possible', 'Lorem ipsum text', NOW(), NOW(), '2024-06-02', '2024-06-06', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'd67b2d18-8c1a-4bbd-8dc4-27d5e3e01f09', 7, 4, 'South Sudan', 'Severe Heat Alert', 'PENDING', '', 'SMS',
        'Excessive heat warning', 'Lorem ipsum text', NOW(), NOW(), '2024-07-14', '2024-07-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '0cbf8a7e-1f44-46c3-bb2e-2e7b2d8dc3b5', 8, 5, 'Djibouti', 'Flash Flood Watch', 'SENT', '', 'SMS',
        'Rapid flooding possible', 'Lorem ipsum text', NOW(), NOW(), '2024-08-22', '2024-08-28', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'a7892f45-8342-41d8-8c8c-5e3b87d3c2d4', 9, 1, 'Kenya', 'Storm Surge Alert', 'PENDING', '', 'SMS',
        'Dangerous water surge', 'Lorem ipsum text', NOW(), NOW(), '2024-09-01', '2024-09-07','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '32cbe1c2-5e5b-49b9-9f8d-9b27f8f8f72f', 10, 6, 'Ethiopia', 'Coastal Warning', 'SENT', '', 'SMS',
        'Heavy coastal winds', 'Lorem ipsum text', NOW(), NOW(), '2024-10-10', '2024-10-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'c8e9b4a2-9c3b-40b8-9c8f-5b3a87d5c9f0', 11, 2, 'Somalia', 'Hail Alert', 'PENDING', '', 'SMS', 'Hailstorm risk',
        'Lorem ipsum text', NOW(), NOW(), '2024-11-05', '2024-11-09', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), 'd4b98eb1-4c5e-4b3b-8f0c-5a87f1d7e4f2', 12, 3, 'Uganda', 'Dust Advisory', 'SENT', '', 'SMS',
        'Low visibility expected', 'Lorem ipsum text', NOW(), NOW(), '2024-12-01', '2024-12-05', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '3b2c8e6d-9f5b-49b2-8c1f-7e3d9b4c8b8c', 13, 4, 'South Sudan', 'Freezing Temps', 'PENDING', '', 'SMS',
        'Extreme cold alert', 'Lorem ipsum text', NOW(), NOW(), '2024-01-25', '2024-01-30', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '5f6d3b7a-4c2e-4a8b-9e4d-6b9f3c8e7d3a', 14, 5, 'Djibouti', 'Fire Weather Alert', 'SENT', '', 'SMS',
        'High fire risk', 'Lorem ipsum text', NOW(), NOW(), '2024-02-10', '2024-02-15','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '1e3a2b4c-5b6d-8f0a-9e4d-7f2c3b8e1f6d', 15, 1, 'Kenya', 'Rain Alert', 'PENDING', '', 'SMS', 'Heavy rain expected',
        'Lorem ipsum text', NOW(), NOW(), '2024-03-08', '2024-03-12', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '6b7d3e5a-9b2f-4c2a-8f3d-5a8e1d3c9f7a', 16, 6, 'Ethiopia', 'Tornado Warning', 'SENT', '', 'SMS',
        'Tornadoes imminent', 'Lorem ipsum text', NOW(), NOW(), '2024-04-15', '2024-04-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '8d9c3b2f-7b6e-4a1f-9c3d-6e7a1f3d8b5c', 17, 2, 'Somalia', 'Heatwave Alert', 'PENDING', '', 'SMS',
        'High temperatures', 'Lorem ipsum text', NOW(), NOW(), '2024-05-05', '2024-05-10', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '3c7d4f8b-6e5a-2b1f-9f2d-8e5d3a9b7c8e', 18, 3, 'Uganda', 'Snow Advisory', 'SENT', '', 'SMS', 'Light snow expected',
        'Lorem ipsum text', NOW(), NOW(), '2024-06-12', '2024-06-18', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '5a7e3c8d-9f6b-4b3d-2e8a-9f3d5c6e2b7a', 19, 4, 'South Sudan', 'Severe Weather', 'PENDING', '', 'SMS',
        'Multiple hazards', 'Lorem ipsum text', NOW(), NOW(), '2024-07-01', '2024-07-05','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '4b2e8f6d-1c5a-3b8a-9e4f-5d3a6c9f7b2d', 20, 5, 'Djibouti', 'Flood Watch', 'SENT', '', 'SMS', 'High flooding risk',
        'Lorem ipsum text', NOW(), NOW(), '2024-08-01', '2024-08-06', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '9d3c8e4b-5f7a-4c9b-8a6d-3e7d2f5a8b6f', 21, 1, 'Kenya', 'Thunder Alert', 'PENDING', '', 'SMS',
        'Thunderstorms likely', 'Lorem ipsum text', NOW(), NOW(), '2024-09-15', '2024-09-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '3f7b8e9d-5d4c-1e2a-9b6f-7e2d3c5a4f8d', 22, 6, 'Ethiopia', 'Cyclone Watch', 'SENT', '', 'SMS', 'Cyclone formation',
        'Lorem ipsum text', NOW(), NOW(), '2024-10-08', '2024-10-13','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '5d4c8e7a-3f2b-1e6d-9a4f-7e9f2a5b8c6f', 23, 2, 'Somalia', 'Winter Storm', 'PENDING', '', 'SMS',
        'Cold and snow predicted', 'Lorem ipsum text', NOW(), NOW(), '2024-11-11', '2024-11-16', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '9e3d8c4a-7f5a-3c2b-8a1e-2e4f8b5f4b8e', 24, 3, 'Uganda', 'High Wind Warning', 'SENT', '', 'SMS',
        'Winds over 50 mph', 'Lorem ipsum text', NOW(), NOW(), '2024-12-05', '2024-12-10','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), 'a4e7b9e1-2b4f-4e8f-9e6f-3a8e1f2e5c9b', 25, 4, 'South Sudan', 'Wildfire Risk', 'PENDING', '', 'SMS',
        'Risk of wildfires', 'Lorem ipsum text', NOW(), NOW(), '2025-01-04', '2025-01-10','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '6a2b8e7c-7f2c-4d9e-b2f4-5a7c6d8a9b2e', 26, 5, 'Djibouti', 'Earthquake Warning', 'SENT', '', 'SMS',
        'Potential earthquake risk', 'Lorem ipsum text', NOW(), NOW(), '2025-02-20', '2025-02-25', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '2c8d5f9a-1e2a-3f5d-9e8e-7f7d3b6e2d9f', 27, 1, 'Kenya', 'Flood Risk', 'PENDING', '', 'SMS',
        'Risk of river flooding', 'Lorem ipsum text', NOW(), NOW(), '2025-03-12', '2025-03-17', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '1b9f6c3d-2d7a-8e6b-9d8f-7b2d3e4f2a3e', 28, 6, 'Ethiopia', 'Volcanic Activity', 'SENT', '', 'SMS',
        'Volcano activity warning', 'Lorem ipsum text', NOW(), NOW(), '2025-04-08', '2025-04-14', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '3a7e9d6a-2f6c-9e1a-8c5e-4a7d8b3a5b9d', 29, 2, 'Somalia', 'Flooding Risk', 'PENDING', '', 'SMS',
        'Heavy rains expected', 'Lorem ipsum text', NOW(), NOW(), '2025-05-02', '2025-05-07', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '4e3b6c8f-1c4b-4e8d-8b6f-5e3f7b9c8b7e', 30, 3, 'Uganda', 'Mudslide Alert', 'SENT', '', 'SMS',
        'Dangerous mudslides possible', 'Lorem ipsum text', NOW(), NOW(), '2025-06-15', '2025-06-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '7a8e5c6a-9f5d-8a7c-9c5e-2e1f6d4b5b6a', 31, 4, 'South Sudan', 'Tsunami Risk', 'PENDING', '', 'SMS',
        'Tsunami warning', 'Lorem ipsum text', NOW(), NOW(), '2025-07-11', '2025-07-15', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '8f3b2e7a-9c2b-7e8c-5e6e-7a9f6b2f8b7c', 32, 5, 'Djibouti', 'Tornado Risk', 'SENT', '', 'SMS', 'Tornadoes forming',
        'Lorem ipsum text', NOW(), NOW(), '2025-08-03', '2025-08-08', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '2e5b3d9c-8f3b-7e9a-9d2e-1c9e4f5d9a6e', 33, 1, 'Kenya', 'Hailstorm Risk', 'PENDING', '', 'SMS',
        'Hailstorm expected', 'Lorem ipsum text', NOW(), NOW(), '2025-09-07', '2025-09-13', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '3a9c5e7d-2f3b-5e6f-9b5a-4d8e6f1b9c9f', 34, 6, 'Ethiopia', 'Avalanche Risk', 'SENT', '', 'SMS',
        'Snow avalanche warning', 'Lorem ipsum text', NOW(), NOW(), '2025-10-15', '2025-10-20', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '6c7b3d2e-9c2a-4e9f-b1d4-8a4e5f6c8d3f', 35, 2, 'Somalia', 'Frost Warning', 'PENDING', '', 'SMS',
        'Heavy frost expected', 'Lorem ipsum text', NOW(), NOW(), '2025-11-01', '2025-11-07','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '3a1f7c3e-9e4d-7b2a-4e8f-9c6e2a7f3b9a', 36, 3, 'Uganda', 'Cold Wave', 'SENT', '', 'SMS', 'Cold front moving in',
        'Lorem ipsum text', NOW(), NOW(), '2025-12-01', '2025-12-06', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '9f3c6e4a-4f7b-8d2f-3e7a-9c1f6b7a3c7f', 37, 4, 'South Sudan', 'Sandstorm Alert', 'PENDING', '', 'SMS',
        'Severe sandstorm expected', 'Lorem ipsum text', NOW(), NOW(), '2026-01-01', '2026-01-06','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '5a9b3c7e-9d5c-4e6e-7c3a-6e8f2a7b7a8d', 38, 5, 'Djibouti', 'Lightning Risk', 'SENT', '', 'SMS',
        'Lightning expected', 'Lorem ipsum text', NOW(), NOW(), '2026-02-08', '2026-02-14','5a2e626c-1222-44a3-b5a2-8cb21167c354'),
       (nextval('broadcast_id_seq'), '4f2c8d3b-7a5e-4a9d-b2c9-9c6d8e1f6b7f', 39, 1, 'Kenya', 'Wind Chill Warning', 'PENDING', '', 'SMS',
        'Cold winds coming', 'Lorem ipsum text', NOW(), NOW(), '2026-03-10', '2026-03-15', '19dac117-3242-4331-b0b4-1e4393e083d4'),
       (nextval('broadcast_id_seq'), '8b3f1e5a-2e4b-7e1f-5e9d-8b2e4e7a3b8e', 40, 6, 'Ethiopia', 'Flood Advisory', 'SENT', '', 'SMS',
        'Rising river levels', 'Lorem ipsum text', NOW(), NOW(), '2026-04-01', '2026-04-06','5a2e626c-1222-44a3-b5a2-8cb21167c354');
