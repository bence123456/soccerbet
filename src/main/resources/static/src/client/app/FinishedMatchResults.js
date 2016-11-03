import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';
import MobileTearSheet from './MobileTearSheet';

class FinishedMatchResult extends React.Component {

	constructor(props) {
		super(props);
		this.state = {matches: []};
	}

	componentDidMount() {
       fetch('http://localhost:8080/api/matches/search/findByStatus?status=FINISHED')
       .then((response) => { return response.json() })
       .then( (json) => {this.setState({matches: json._embedded.matches}); });
	}

	render() {
        var matchNodes = this.state.matches.map(function (match, i) {
			var homeTeamName = match.homeTeamName;
			var awayTeamName = match.awayTeamName;
			var homeTeamLogoSrc = "/images/logos/" + homeTeamName.replace("/", "") + ".png";
			var awayTeamLogoSrc = "/images/logos/" + awayTeamName + ".png";
			
			var homeStyle = {}, awayStyle = {}, wonStyle = {fontWeight: 'bold'};
			if (match.matchResult === "HOME_TEAM_WINS") {
				homeStyle = wonStyle;
			} else if (match.matchResult === "AWAY_TEAM_WINS") {
				awayStyle = wonStyle;
			}

            return (
				<List >
					<ListItem style = {homeStyle}
						primaryText = {homeTeamName}
						rightAvatar = { <Avatar> {match.homeTeamGoals} </Avatar> }
						leftAvatar = {<Avatar src = {homeTeamLogoSrc} />} />
					<ListItem style = {awayStyle}
						primaryText = {awayTeamName}
						rightAvatar = { <Avatar> {match.awayTeamGoals} </Avatar> }
						leftAvatar = {<Avatar src = {awayTeamLogoSrc} />} />
				</List>
            );
        });
		
		var mobileTearSheets = matchNodes.map(function (matchNode, i) {
			var sheets = [];
				if (i % 4 == 0) {
					const sheet = (
						<MobileTearSheet key={i}>
							{matchNodes[i]}
							<Divider/>
							{matchNodes[i+1]}
							<Divider/>
							{matchNodes[i+2]}
							<Divider/>
							{matchNodes[i+3]}
						</MobileTearSheet>
					);
					sheets.push(sheet);
				}
			
			return sheets;
		});

        return (
			<div style={{display: 'flex', flexWrap: 'wrap'}}>
				{mobileTearSheets}
			</div>
        );
	}
}

export default FinishedMatchResult;