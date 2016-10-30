import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';

class UpcomingMatches extends React.Component {

	constructor(props) {
		super(props);
		this.state = {matches: []};
	}

	componentDidMount() {
       fetch('http://localhost:8080/api/matches/search/findByStatus?status=TIMED')
       .then((response) => { return response.json() })
       .then( (json) => {this.setState({matches: json._embedded.matches}); });
	}

	render() {
        var matchNodes = this.state.matches.map(function (match, i) {
			var homeTeamName = match.homeTeamName; 
			var awayTeamName = match.awayTeamName;
			var homeTeamLogoSrc = "/images/" + homeTeamName.replace("/", "") + ".png";
			var awayTeamLogoSrc = "/images/" + awayTeamName + ".png";
			
              return (
				<div key={i}> 
					<List >
						<ListItem
							primaryText = {homeTeamName}
							rightAvatar = { <Avatar> {match.homeTeamGoals} </Avatar> }
							leftAvatar = {<Avatar src = {homeTeamLogoSrc} />} />
						<ListItem
							primaryText = {awayTeamName}
							rightAvatar = { <Avatar> {match.awayTeamGoals} </Avatar> }
							leftAvatar = {<Avatar src = {awayTeamLogoSrc} />} />
					</List>
				    <Divider/>
				</div>
              );
        });

        return (
			<div>
				{matchNodes}
			</div>
        );
	}
}

export default UpcomingMatches;