<tpl id="message-template">
	<article class="topNews">
		<section style="overflow: hidden;" class="newsList">
			<ul page="0" class="newsPage" id="newsListContent">
				{{#each item}}
				
				{{#each multi_item}}
				<li class="newsHead">
					<a target="_bank" href="{{content_url}}">
						<img src="{{cover}}" width="70" height="52">
						<div>
							<p class="newsTitle">{{title}}</p>
							<p>{{digest}}</p>
							<span class="newsTips">{{create_time}}</span>
						</div>
					</a>
				</li>
				{{/each}}
				{{/each}}
			</ul>
		</section>
	</article>
</tpl>