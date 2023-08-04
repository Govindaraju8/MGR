do $$
DECLARE 
	trans_cursor CURSOR FOR SELECT tr_type,amount from transaction where accno=102 and tr_date > '2021-04-01';
	tt char;amt numeric;cb numeric;
Begin
	select balance into cb from accounts where accno=102;
	OPEN trans_cursor;
	Loop
    		FETCH trans_cursor INTO tt,amt;
	  	Exit When not found;
    		if tt='D' then
			cb:=cb-amt;
    		elsif tt='W' then
  			cb:=cb+amt;
    		END If;
	end loop;
	Raise Notice 'Balance as on the given Date :%',cb;
	CLOSE trans_cursor;
End; $$ language plpgsql;