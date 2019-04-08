(define (domain du3)
	(:requirements :strips :typing)
	(:types place ship item final_status sale_item story_item sea_status gold_item flag_status alco_status transport_status)
	(:predicates
		(neighbours ?from ?to ?status)
		
		(in ?place)

		(has_item ?item)
		
		(has_status ?status)
		
		(one_drink ?alcohol ?status ?next_status)
		(two_drink ?alcohol ?status ?next_status)
		(final_drink ?alcohol ?status ?next_status)

		(can_study ?where ?prison_status)
		(up_salior_capitan ?status ?next_status ?item ?girl)
		(up_capitan_admiral ?status ?next_status ?pirates ?alco ?winner)
		
		(need_to_boat ?boat ?wood ?transport_status)
		(need_to_fregat ?fregat ?boat ?wood ?granule)
		(need_to_caravel ?caravel ?boat ?wood ?coin)

		(forest_action ?where)
		(is_wood ?wood)
		(is_flower ?flower)
		(bear_fight ?bear_skin ?fight ?girl)
		(change_in_forest ?alco ?map ?bad_friend)

		(river_action ?where)
		(steal_boat ?boat ?prison ?swim)
		(is_gold_granule ?gold)
		(list_alco_status ?sober ?funny ?drunk ?intox)

		(dock_action ?where)
		(is_gold_coin ?coin)
		(is_bear_skin ?bear_skin)
		(is_coconut ?coconut)
		(meet_smugglers ?gold_brick ?bad_friend ?smuggler)

		(bar_action ?where)
		(buy_alco ?alcohol ?gold_granule)
		(buy_alco_for_all ?gold_coin ?good_friend)
		(fight_in_bar ?sober ?fight)

		(city_action ?where)
		(farm_money ?gold_granule ?gold_coin ?good_friend)
		(invest_money ?gold_coin ?gold_brick ?good_friend)
		(steal_money ?gold_coin ?prison)
		(pay_for_prison_free ?gold_granule ?prison)
		(work_for_prison_free ?prison)

		(sea_action ?where)
		(gold_list ?gold_granule ?gold_coin ?gold_brick)
		(ship_list ?boat ?fregat ?caravel)
		(is_fight ?fight)
		(is_pirates ?pirates)
		(is_bad_friend ?bad_friend)
		(is_perl ?perl)
		(is_girl ?girl)

		(seamark_action ?where)
		(gift_for_girl ?gold_brick ?perl ?flower ?girl ?bride)

		(island_action ?where)
		(find_cocaine ?map ?cocaine)

		(need_for_wending ?bride ?good_friend ?prison ?sober ?married ?winner)

		(nead_for_drugsdealer ?cocaine ?intox ?fregat ?smuggler ?gold_brick ?drugsdealer ?winner)
	)


	(:action move
		:parameters (?from - place ?to - place ?transport - transport_status)
		:precondition (and 
			(in ?from)
			(has_status ?transport)
			(neighbours ?from ?to ?transport)
		)
		:effect (and
			(not(in ?from))
			(in ?to)
		)
	)

	(:action first_drink
		:parameters (?alcohol - item ?status - alco_status ?next_status - alco_status)
		:precondition (and 
			(one_drink ?alcohol ?status ?next_status)
			(has_item ?alcohol)
			(has_status ?status)
		)
		:effect (and
			(not(has_item ?alcohol))
			(not(has_status ?status))
			(has_status ?next_status)
		)
	)

	(:action second_drink
		:parameters (?alcohol - item ?status - alco_status ?next_status - alco_status)
		:precondition (and 
			(two_drink ?alcohol ?status ?next_status)
			(has_item ?alcohol)
			(has_status ?status)
		)
		:effect (and
			(not(has_item ?alcohol))
			(not(has_status ?status))
			(has_status ?next_status)
		)
	)

	(:action last_drink
		:parameters (?alcohol - item ?status - alco_status ?next_status - alco_status)
		:precondition (and 
			(final_drink ?alcohol ?status ?next_status)
			(has_item ?alcohol)
			(has_status ?status)
		)
		:effect (and
			(not(has_item ?alcohol))
			(not(has_status ?status))
			(has_status ?next_status)
		)
	)

	(:action up_to_capitan
		:parameters (?where - place ?prison - flag_status ?status - sea_status ?next_status - sea_status ?coin - gold_item ?girl - flag_status)
		:precondition (and 
			(in ?where)
			(can_study ?where ?prison)
			(up_salior_capitan ?status ?next_status ?coin ?girl)
			(not(has_status ?prison))
			(has_status ?status)
			(has_item ?coin)
		)
		:effect (and
			(not(has_status ?status))
			(not(has_item ?coin))
			(has_status ?next_status)
			(has_item ?girl)
		)
	)

	(:action up_to_admiral
		:parameters (?where - place ?prison - flag_status ?status - sea_status ?next_status - sea_status ?pirates - flag_status ?sober - alco_status ?winner - final_status)
		:precondition (and 
			(in ?where)
			(can_study ?where ?prison)
			(up_capitan_admiral ?status ?next_status ?pirates ?sober ?winner)
			(not(has_status ?prison))
			(has_status ?status)
			(has_status ?pirates)
			(has_status ?sober)
		)
		:effect (and
			(not(has_status ?status))
			(has_status ?next_status)
			(has_status ?winner)
		)
	)

	(:action make_boat
		:parameters (?boat - ship ?wood - item ?swim - transport_status)
		:precondition (and 
			(not(has_item ?boat))
			(need_to_boat ?boat ?wood ?swim)
			(has_item ?wood)
		)
		:effect (and
			(not(has_item ?wood))
			(has_status ?swim)
			(has_item ?boat)
		)
	)

	(:action make_fregat
		:parameters (?fregat - ship ?boat - ship ?wood - item ?granule - gold_item)
		:precondition (and 
			(not(has_item ?fregat))
			(need_to_fregat ?fregat ?boat ?wood ?granule)
			(has_item ?boat)
			(has_item ?wood)
			(has_item ?granule)
		)
		:effect (and
			(not(has_item ?boat))
			(not(has_item ?wood))
			(not(has_item ?granule))
			(has_item ?fregat)
		)
	)

	(:action make_caravel
		:parameters (?caravel - ship ?boat - ship ?wood - item ?coin - gold_item)
		:precondition (and 
			(not(has_item ?caravel))
			(need_to_caravel ?caravel ?boat ?wood ?coin)
			(has_item ?boat)
			(has_item ?wood)
			(has_item ?coin)
		)
		:effect (and
			(not(has_item ?boat))
			(not(has_item ?wood))
			(not(has_item ?coin))
			(has_item ?caravel)
		)
	)

	(:action get_wood_in_forest
		:parameters (?forest - place ?wood - item)
		:precondition (and 
			(forest_action ?forest)
			(in ?forest)
			(is_wood ?wood)
			(not(has_item ?wood))
		)
		:effect (and
			(has_item ?wood)
		)
	)

	(:action get_flower_in_forest
		:parameters (?forest - place ?flower - story_item)
		:precondition (and 
			(forest_action ?forest)
			(in ?forest)
			(is_flower ?flower)
			(not(has_item ?flower))
		)
		:effect (and
			(has_item ?flower)
		)
	)

	(:action fight_with_bear_in_forest
		:parameters (?forest - place ?bear_skin - sale_item ?fight - flag_status ?girl - flag_status)
		:precondition (and 
			(forest_action ?forest)
			(in ?forest)
			(bear_fight ?bear_skin ?fight ?girl)
			(not(has_item ?bear_skin))
		)
		:effect (and
			(has_item ?bear_skin)
			(has_status ?fight)
			(has_status ?girl)
		)
	)

	(:action change_map_alco_in_forest
		:parameters (?forest - place ?alcohol - item ?map - story_item ?bad_friend - flag_status)
		:precondition (and 
			(forest_action ?forest)
			(in ?forest)
			(change_in_forest ?alcohol ?map ?bad_friend)
			(not(has_item ?map))
			(has_item ?alcohol)
		)
		:effect (and
			(not(has_item ?alcohol))
			(has_item ?map)
			(has_status ?bad_friend)
		)
	)

	(:action steal_boat_in_river
		:parameters (?river - place ?boat - ship ?prison - flag_status ?swim - transport_status)
		:precondition (and
			(in ?river)
			(river_action ?river)
			(steal_boat ?boat ?prison ?swim) 
			(not(has_item ?boat))
		)
		:effect (and
			(has_item ?boat)
			(has_status ?prison)
			(has_status ?swim)
		)
	)

	(:action get_gold_granule_in_river 
		:parameters (?river - place ?gold_granule - gold_item)
		:precondition (and
			(in ?river)
			(river_action ?river)
			(is_gold_granule ?gold_granule)
			(not(has_item ?gold_granule))
		)
		:effect (and
			(has_item ?gold_granule)
		)
	)

	(:action cold_water_in_river
		:parameters (?river - place ?sober - alco_status ?funny - alco_status ?drunk - alco_status ?intox - alco_status)
		:precondition (and
			(river_action ?river)
			(in ?river)
			(list_alco_status ?sober ?funny ?drunk ?intox)
			(not(has_status ?sober))
			(not(has_status ?intox))	
		)
		:effect (and
			(not(has_status ?funny))
			(not(has_status ?drunk))
			(has_status ?sober)
		)
	)

	(:action work_in_dock 
		:parameters (?dock - place ?gold_granule - gold_item)
		:precondition (and
			(dock_action ?dock)
			(in ?dock)
			(is_gold_granule ?gold_granule)
			(not(has_item ?gold_granule))
		)
		:effect (and
			(has_item ?gold_granule)
		)
	)

	(:action sale_bear_skin_in_dock
		:parameters (?dock - place ?bear_skin - sale_item ?gold_coin - gold_item)
		:precondition (and
			(dock_action ?dock)
			(in ?dock)
			(is_bear_skin ?bear_skin)
			(has_item ?bear_skin)
			(is_gold_coin ?gold_coin)
			(not(has_item ?gold_coin))
		)
		:effect (and
			(not(has_item ?bear_skin))
			(has_item ?gold_coin)
		)
	)

	(:action sale_coconut_in_dock
		:parameters (?dock - place ?coconut - sale_item ?gold_coin - gold_item)
		:precondition (and
			(dock_action ?dock)
			(in ?dock)
			(is_coconut ?coconut)
			(has_item ?coconut)
			(is_gold_coin ?gold_coin)
			(not(has_item ?gold_coin))
		)
		:effect (and
			(not(has_item ?coconut))
			(has_item ?gold_coin)
		)
	)

	(:action meet_smugglers_in_dock
		:parameters (?dock - place ?gold_brick - gold_item ?bad_friend - flag_status ?smuggler - flag_status)
		:precondition (and
			(dock_action ?dock)
			(in ?dock)
			(meet_smugglers ?gold_brick ?bad_friend ?smuggler)
			(has_item ?gold_brick)
			(has_status ?bad_friend)
		)
		:effect (and
			(has_status ?smuggler)
		)
	)

	(:action buy_alco_in_bar 
		:parameters (?bar - place ?alcohol - item ?gold_granule - gold_item)
		:precondition (and
			(bar_action ?bar)
			(in ?bar)
			(buy_alco ?alcohol ?gold_granule)
			(not(has_item ?alcohol))
			(has_item ?gold_granule)
		)
		:effect (and
			(not(has_item ?gold_granule))
			(has_item ?alcohol)
		)
	)

	(:action buy_alco_for_all_in_bar
		:parameters (?bar - place ?gold_coin - gold_item ?good_friend - flag_status)
		:precondition (and
			(bar_action ?bar)
			(in ?bar)
			(buy_alco_for_all ?gold_coin ?good_friend)
			(not(has_status ?good_friend))
			(has_item ?gold_coin)
		)
		:effect (and
			(not(has_item ?gold_coin))
			(has_status ?good_friend)
		)
	)

	(:action have_fight_in_bar
		:parameters (?bar - place ?sober - alco_status ?fight - flag_status)
		:precondition (and
			(bar_action ?bar)
			(in ?bar)
			(fight_in_bar ?sober ?fight)
			(not(has_status ?fight))
			(not(has_status ?sober))
		)
		:effect (and
			(has_status ?fight)
		)
	)

	(:action farm_money_in_city
		:parameters (?city - place ?gold_granule - gold_item ?gold_coin - gold_item ?good_friend - flag_status)
		:precondition (and
			(city_action ?place)
			(in ?city)
			(farm_money ?gold_granule ?gold_coin ?good_friend)
			(has_item ?gold_granule)
			(not(has_item ?gold_coin))
		)
		:effect (and
			(not(has_item ?gold_granule))
			(has_item ?gold_coin)
			(has_status ?good_friend)
		)
	)

	(:action invest_money_in_city
		:parameters (?city - place ?gold_coin - gold_item ?gold_brick - gold_item ?good_friend - flag_status)
		:precondition (and
			(city_action ?place)
			(in ?city)
			(invest_money ?gold_coin ?gold_brick ?good_friend)
			(has_item ?gold_coin)
			(not(has_item ?gold_brick))
		)
		:effect (and
			(not(has_item ?gold_coin))
			(has_item ?gold_brick)
			(has_status ?good_friend)
		)
	)

	(:action steal_money_in_city
		:parameters (?city - place ?gold_coin - gold_item ?prison - flag_status)
		:precondition (and
			(city_action ?place)
			(in ?city)
			(steal_money ?gold_coin ?prison)
			(not(has_item ?gold_coin))
		)
		:effect (and
			(has_item ?gold_coin)
			(has_status ?prison)
		)
	)

	(:action pay_for_prison_free_in_city
		:parameters (?city - place ?gold_granule - gold_item ?prison - flag_status)
		:precondition (and
			(city_action ?place)
			(in ?city)
			(pay_for_prison_free ?gold_granule ?prison)
			(has_status ?prison)
			(has_item ?gold_granule)
		)
		:effect (and
			(not(has_status ?prison))
			(not(has_item ?gold_granule))
		)
	)

	(:action work_for_prison_free_in_city
		:parameters (?city - place ?prison - flag_status ?sober - alco_status ?funny - alco_status ?drunk - alco_status ?intox - alco_status)
		:precondition (and
			(city_action ?place)
			(in ?city)
			(work_for_prison_free ?prison)
			(has_status ?prison)
			(list_alco_status ?sober ?funny ?drunk ?intox)				
		)
		:effect (and
			(not(has_status ?prison))
			(not(has_status ?sober))
			(not(has_status ?drunk))
			(not(has_status ?intox))
			(has_status ?funny)
		)
	)

	(:action lose_fight_in_sea
		:parameters (?sea - place ?fight - flag_status ?bad_friend - flag_status ?boat - ship ?fregat - ship ?caravel - ship ?gold_granule - gold_item ?gold_coin - gold_item ?gold_brick - gold_item)
		:precondition (and
			(sea_action ?sea)
			(in ?sea)
			(is_fight ?fight)
			(is_bad_friend ?bad_friend)
			(not(has_status ?fight))
			(not(has_status ?bad_friend))
			(ship_list ?boat ?fregat ?caravel)
			(gold_list ?gold_granule ?gold_coin ?gold_brick)
		)
		:effect (and
			(not(has_item ?fregat))
			(not(has_item ?caravel))
			(not(has_item ?gold_granule))
			(not(has_item ?gold_coin))
			(not(has_item ?gold_brick))
			(has_item ?boat)
			(has_status ?fight)
		)
	)

	(:action add_to_pirates_in_sea
		:parameters (?sea - place ?bad_friend - flag_status ?sober - alco_status ?funny - alco_status ?drunk - alco_status ?intox - alco_status)
		:precondition (and
			(sea_action ?sea)
			(in ?sea)
			(is_bad_friend ?bad_friend)
			(has_status ?bad_friend)
			(list_alco_status ?sober ?funny ?drunk ?intox)
		)
		:effect (and
			(not(has_status ?sober))
			(not(has_status ?drunk))
			(not(has_status ?intox))
			(has_status ?funny)
		)
	)

	(:action win_fight_in_sea
		:parameters (?sea - place ?fight - flag_status ?pirates - flag_status ?bad_friend - flag_status ?boat - ship ?fregat - ship ?caravel - ship ?gold_granule - gold_item ?gold_coin - gold_item ?gold_brick - gold_item ?girl - flag_status)
		:precondition (and
			(sea_action ?sea)
			(in ?sea)
			(is_fight ?fight)
			(is_pirates ?pirates)
			(has_status ?fight)
			(ship_list ?boat ?fregat ?caravel)
			(has_item ?caravel)
			(gold_list ?gold_granule ?gold_coin ?gold_brick)
			(is_girl ?girl)
		)
		:effect (and
			(has_item ?gold_granule)
			(has_item ?gold_coin)
			(has_item ?gold_brick)
			(has_item ?boat)
			(has_item ?fregat)
			(has_status ?girl)
			(has_status ?pirates)
		)
	)

	(:action get_perl_in_sea
		:parameters (?sea - place ?perl - story_item)
		:precondition (and
			(sea_action ?sea)
			(in ?sea)
			(is_perl ?perl)
			(not(has_item ?perl))
		)
		:effect (and
			;;;;;;;;;
			(not(has_item ?perl))
		)
	)

	(:action cold_water_in_sea 
		:parameters (?sea - place ?sober - alco_status ?funny - alco_status ?drunk - alco_status ?intox - alco_status)
		:precondition (and
			(sea_action ?sea)
			(in ?sea)
			(list_alco_status ?sober ?funny ?drunk ?intox)
			(not(has_status ?sober))
			(not(has_status ?intox))
		)
		:effect (and
			(not(has_status ?funny))
			(not(has_status ?drunk))
			(has_status ?sober)
		)
	)

	(:action present_gift_in_seamark
		:parameters (?seamark - place ?gold_brick - gold_item ?perl - story_item ?flower - story_item ?girl - flag_status ?bride - flag_status)
		:precondition (and
			(seamark_action ?seamark)
			(in ?seamark)
			(gift_for_girl ?gold_brick ?perl ?flower ?girl ?bride)
			(has_item ?gold_brick)
			(has_item ?perl)
			(has_item ?flower)
			(has_status ?girl)
			(not(has_status ?bride))
		)
		:effect (and
			(not(has_item ?gold_brick))
			(not(has_item ?perl))
			(not(has_item ?flower))
			(has_status ?bride)
		)
	)

	(:action get_coconut_in_island
		:parameters (?island - place ?coconut - sale_item)
		:precondition (and
			(island_action ?island)
			(in ?island)
			(is_coconut ?coconut)
			(not(has_item ?coconut))
		)
		:effect (and
			(has_item ?coconut)
		)
	)

	(:action get_wood_in_island
		:parameters (?island - place ?wood - item)
		:precondition (and
			(island_action ?island)
			(in ?island)
			(is_wood ?wood)
			(not(has_item ?wood))
		)
		:effect (and
			(has_item ?wood)
		)
	)	

	(:action find_cocaine_in_island
		:parameters (?island - place ?map - story_item ?cocaine - story_item)
		:precondition (and
			(island_action ?island)
			(in ?island)
			(find_cocaine ?map ?cocaine)
			(not(has_item ?cocaine))
			(has_item ?map)
		)
		:effect (and
			(has_item ?cocaine)
		)
	)

	(:action wending
		:parameters (?island - place ?bride - flag_status ?good_friend - flag_status ?prison - flag_status ?sober - alco_status ?married - final_status ?winner - final_status)
		:precondition (and
			(island_action ?island)
			(in ?island)
			(need_for_wending ?bride ?good_friend ?prison ?sober ?married ?winner)
			(has_status ?bride)
			(has_status ?good_friend)
			(not(has_status ?prison))
			(has_status ?sober)
			(not(has_status ?married))
		)
		:effect (and
			(has_status ?married)
			(has_status ?winner)
		)
	)

	(:action become_drugsdealer
		:parameters (?cocaine - story_item ?intox - alco_status ?fregat - ship ?smuggler - flag_status ?gold_brick - gold_item ?drugsdealer - final_status ?winner - final_status)
		:precondition (and
			(nead_for_drugsdealer ?cocaine ?intox ?fregat ?smuggler ?gold_brick ?drugsdealer ?winner)
			(not(has_status ?drugsdealer))
			(has_item ?cocaine)
			(has_item ?gold_brick)
			(has_item ?fregat)
			(has_status ?intox)
			(has_status ?smuggler)
		)
		:effect (and
			(has_status ?drugsdealer)
			(has_status ?winner)
		)
	)
)