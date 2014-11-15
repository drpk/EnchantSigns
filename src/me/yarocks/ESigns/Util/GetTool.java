/*
 * Copyright 2014 yarocks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package me.yarocks.ESigns.Util;/**
 * Created by User Name on 10/23/2014.
 */

import me.yarocks.ESigns.Main;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by User Name at 1:00 PM on 10/23/2014
 *
 * @author yarocks
 *         © 2014 yarocks
 *         All Rights Reserved.
 */

public class GetTool {
// ? statements:
// oolean statement ? true result : false result;


	public Main c;

	public GetTool(Main c) {
		this.c = c;
	}

	public  boolean isEnchantable(Player player){
		List<String> enchantlist = c.getConfig().getStringList("Enchantable-Items");
		if (!c.getConfig().getStringList("Enchantable-Items").isEmpty()){
			return enchantlist.contains(player.getItemInHand().getType().toString());
		}
		return false;
	}

}

