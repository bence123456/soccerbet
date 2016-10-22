'use strict';

class Matches extends React.Component {

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
		return (
			<MatchList matches={this.state.matches}> </MatchList>
		)
	}
}

class MatchList extends React.Component{
	render() {
		var matches = this.props.matches.map(match =>
			<Match key={match._links.self.href} match={match}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Home Team</th>
					</tr>
					{matches}
				</tbody>
			</table>
		)
	}
}

class Match extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.matches.homeTeamName}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<Matches />,
	document.getElementById('matches')
)